package org.sai.tools.fe.rest;

import com.google.common.collect.Sets;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.sai.featureeye.domain.Feature;
import org.sai.featureeye.gherkin.Element;
import org.sai.featureeye.gherkin.FeatureFileFragment;
import org.sai.tools.fe.validator.FeatureValidator;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * Created by saipkri on 19/02/16.
 */
@Api(value = "API Endpoint that is used to search the features from the underlying repository")
@RestController
public class FeatureResource {

    private static final Logger LOG = Logger.getLogger(FeatureResource.class);

    private final MongoTemplate mongoTemplate;

    @Inject
    public FeatureResource(final MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @ApiOperation(value = "Endpoint operation to return all the features summary (without the feature content)")
    @RequestMapping(value = "/features-summary", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Feature>> featuresSummary() {
        LOG.info("Get All featuresSummary");
        List<Feature> features = mongoTemplate.findAll(Feature.class)
                .stream()
                .map(f -> {
                    f.setRawContents(null);
                    return f;
                })
                .collect(toList());

        return new ResponseEntity<List<Feature>>(features, HttpStatus.OK);
    }

    @RequestMapping(value = "/feature-models", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<FeatureFileFragment>> featureModel() {
        LOG.info("Get All featureModel");
        List<FeatureFileFragment> features = mongoTemplate.findAll(FeatureFileFragment.class);

        return new ResponseEntity<List<FeatureFileFragment>>(features, HttpStatus.OK);
    }

    @RequestMapping(value = "/feature/{featureId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Feature> featureDetail(@PathVariable("featureId") final String featureId) {
        LOG.info("Get All featuresSummary");
        Query q = new Query();
        q.addCriteria(Criteria.where("id").is(featureId));
        return new ResponseEntity<Feature>(mongoTemplate.findOne(q, Feature.class), HttpStatus.OK);
    }

    @RequestMapping(value = "/featureModel/{featureId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<FeatureFileFragment> featureModel(@PathVariable("featureId") final String featureId) {
        LOG.info("Get featureModel");
        Query q = new Query();
        q.addCriteria(Criteria.where("featureId").is(featureId));
        return new ResponseEntity<FeatureFileFragment>(mongoTemplate.findOne(q, FeatureFileFragment.class), HttpStatus.OK);
    }

    @RequestMapping(value = "/validate", method = RequestMethod.POST, produces = "text/plain")
    public ResponseEntity<String> validate(@RequestBody final String featureContents) {
        LOG.info("validate");
        return new ResponseEntity<String>("", HttpStatus.OK);
    }

    @RequestMapping(value = "/features", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Feature>> featuresSearch(@RequestParam(value = "tags", required = false) final String tags, @RequestParam("featureText") final String featureText) {
        String _tags = tags;
        if (_tags == null) {
            _tags = "";
        }
        LOG.info("featuresSearch: " + featureText);
        Query q = new Query();
        q.addCriteria(Criteria.where("rawContents").regex(Pattern.compile(featureText.trim(), Pattern.CASE_INSENSITIVE)));
        List<Feature> featuresMatchedByText = mongoTemplate.find(q, Feature.class);
        System.out.println(featuresMatchedByText);
        List<String> tagTokens = Arrays.asList(_tags.split(" ")).stream().map(t -> t.replace("@", "").trim()).collect(Collectors.toList());
        List<Feature> filtered = featuresMatchedByText.stream()
                .filter(f -> tagTokens.stream().allMatch(tag -> f.getRawContents().contains("@" + tag)))
                .collect(toList());

        return new ResponseEntity<>(filtered, HttpStatus.OK);
    }

    @RequestMapping(value = "/scenarios/{featureId}", method = RequestMethod.GET, produces = "text/plain")
    public ResponseEntity<String> filteredScenarios(@PathVariable("featureId") final String featureId, @RequestParam(value = "tags", required = true) final String tags) {
        LOG.info("Filter scenarios " + tags);
        Query q = new Query();
        q.addCriteria(Criteria.where("featureId").is(featureId));
        FeatureFileFragment featureFileFragment = mongoTemplate.findOne(q, FeatureFileFragment.class);
        List<String> tagTokens = Arrays.asList(tags.split(" ")).stream().map(t -> t.replace("@", "").trim()).collect(Collectors.toList());
        List<Element> filteredScenarios = featureFileFragment.getElements().stream()
                .filter(element -> element.getTags() != null)
                .filter(element -> Sets.intersection(Stream.of(element.getTags()).map(tag -> tag.getName().replace("@", "").trim()).collect(toSet()), new HashSet<>(tagTokens)).size() > 0)
                .collect(toList());
        String featureHeader = featureFileFragment.getAdditionalProperties().get("keyword") + ": " + featureFileFragment.getAdditionalProperties().get("name") + "\n" + featureFileFragment.getAdditionalProperties().get("description");
        String filteredContent = featureHeader + "\n\n" + filteredScenarios.stream().map(e -> toText(e)).collect(joining("\n"));

        return new ResponseEntity<String>(filteredContent, HttpStatus.OK);
    }

    private String toText(final Element e) {
        String scenarioText = Stream.of(e.getTags())
                .map(t -> t.getName())
                .collect(joining(" "))
                + "\n" + e.getKeyword() + ": " + e.getName()
                + "\n"
                + e.getSteps().stream().map(s -> s.getKeyword() + " " + s.getName()).collect(joining("\n"));
        scenarioText += "\n";
        if (e.getExamples() != null && e.getExamples().size() > 0) {
            scenarioText += "\nExamples: \n";
            scenarioText += e.getExamples().stream()
                    .flatMap(ex -> ex.getRows().stream().map(r -> r.getCells().stream().map(c -> "  |  " + c + "\t\t\t|  ").collect(joining(" "))))
                    .peek(System.out::println)
                    .collect(joining("\n"));
        }
        scenarioText += "\n";
        return scenarioText;
    }
}
