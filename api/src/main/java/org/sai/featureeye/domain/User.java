package org.sai.featureeye.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;


/**
 * Created by saikris on 24/03/2016.
 */
@Data
public class User {
    @Id
    private String id;
    private String userId;
    private String password;
    private String email;
}
