package org.sai.featureeye.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Created by saikris on 24/03/2016.
 */
@Data
public class UserSubscription {
    @Id
    private String id;
    private String featureId;
    private String userId;
}
