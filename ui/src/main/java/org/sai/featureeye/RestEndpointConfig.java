package org.sai.featureeye;

/**
 * Created by saipkri on 25/03/16.
 */
public class RestEndpointConfig {

    public static final String FEATURES_SUMMARY_ENDPOINT_URI = "http://localhost:8080/features-summary";
    public static final String FEATURE_DETAIL_ENDPOINT_URI = "http://localhost:8080/feature/%s";
    public static final String FILTERED_SCENARIOS_ENDPOINT_URI = "http://localhost:8080/scenarios/%s?tags=%s";
    public static final String SEARCH_FEATURES_ENDPOINT_URI = "http://localhost:8080/features?tags=%s&featureText=%s";
    public static final String FEATURE_MODEL_ENDPOINT_URI = "http://localhost:8080/featureModel/%s";
    public static final String FEATURE_MODELS_ENDPOINT_URI = "http://localhost:8080/feature-models";
    public static final String VALIDATE_FEATURE_ENDPOINT_URI = "http://localhost:8080/validate";
    public static final String PUBLISH_FEATURE_ENDPOINT_URI = "http://localhost:8080/publish-feature?author=%s";
}
