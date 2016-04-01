package org.sai.tools.fe.rest;

import com.mongodb.BasicDBObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.sai.featureeye.domain.RunResult;
import org.sai.tools.fe.AppProperties;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by saipkri on 19/02/16.
 */
@Api(value = "API Endpoint that is used to save and list the run results")
@RestController
public class RunResultResource {

    private static final Logger LOG = Logger.getLogger(RunResultResource.class);

    private final MongoTemplate mongoTemplate;
    private final AppProperties appProperties;

    @Inject
    public RunResultResource(final MongoTemplate mongoTemplate, final AppProperties appProperties) {
        this.mongoTemplate = mongoTemplate;
        this.appProperties = appProperties;
    }


    @ApiOperation(value = "Endpoint operation to save the run result")
    @RequestMapping(value = "/run-result", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> saveRunResult(@RequestBody final List<List<Map<String, Object>>> cucumberJsons,
                                           @RequestParam("buildId") final String buildId,
                                           @RequestParam("version") final String version,
                                           @RequestParam("buildLabel") final String buildLabel,
                                           @RequestParam("buildServer") final String buildServer,
                                           @RequestParam("buildParameters") final String buildParameters
    ) throws Exception {
        LOG.info("save run result " + cucumberJsons);
        RunResult runResult = new RunResult();
        runResult.setVersion(version.trim());
        runResult.setBuildLabel(buildLabel.trim());
        runResult.setBuildId(buildId.trim().replace(":", "_").replace(" ", ""));
        runResult.setBuildServer(buildServer.trim());
        runResult.setBuildParameters(buildParameters.trim());
        runResult.setCucumberJsons(cucumberJsons.stream().map(l -> new BasicDBObject(l.get(0))).collect(Collectors.toList()));
        mongoTemplate.save(runResult);

        File dir = new File(appProperties.getReportsDir() + File.separator + runResult.getBuildId().replace(":", "_").replace(" ", ""));
        FileUtils.deleteQuietly(dir);
        List<File> jsons = new ArrayList<>();

        FileUtils.forceMkdir(dir);
        runResult.getCucumberJsons().forEach(doc -> {
            try {
                String fileName = doc.get("id").toString() + ".json";
                File jsonFile = new File(dir.getAbsolutePath() + File.separator + fileName);
                FileUtils.writeStringToFile(jsonFile, "[" + doc.toString() + "]");
                jsons.add(jsonFile);
                Configuration conf = new Configuration(dir, runResult.getBuildId() + " - " + runResult.getBuildLabel());
                ReportBuilder rb = new ReportBuilder(jsons.stream().map(f -> f.getAbsolutePath()).collect(Collectors.toList()), conf);
                rb.generateReports();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/run-results", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<RunResult>> allRunResults() {
        return new ResponseEntity<>(mongoTemplate.findAll(RunResult.class).sort((r1, r2) -> r2.getBuildId().compareTo(r1.getBuildId())), HttpStatus.OK);
    }
}
