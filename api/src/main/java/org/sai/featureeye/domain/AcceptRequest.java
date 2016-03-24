package org.sai.featureeye.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Created by saikris on 24/03/2016.
 */
@Data
public class AcceptRequest {
    @Id
    private String id;
    private Feature feature;
}
