package org.sai.featureeye.domain;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saipkri on 29/03/16.
 */
@Data
public class RunResult {
    @Id
    private String id;
    private String buildId;
    private String version;
    private String buildLabel;
    private String buildServer;
    private String buildParameters;
    private List<BasicDBObject> cucumberJsons = new ArrayList<>();
}
