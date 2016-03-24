package org.sai.featureeye.domain;

import lombok.Data;

/**
 * Created by saikris on 24/03/2016.
 */
@Data
public class Feature {
    private String id;
    private String fileName;
    private String location;
    private String rawContents;
}
