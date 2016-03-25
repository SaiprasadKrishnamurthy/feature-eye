package org.sai.featureeye.controller;

import org.sai.featureeye.RestEndpointConfig;
import org.sai.featureeye.domain.Feature;
import org.springframework.web.client.RestTemplate;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by saipkri on 25/03/16.
 */
public class FeatureController {

    private List<Feature> featureSummary = new ArrayList<>();

    private Feature currentFeature;

    private String scenarioTags;

    private String filteredScenarioText;

    private boolean filter;


    public FeatureController() {
        RestTemplate rt = new RestTemplate();
        Feature[] features = rt.getForObject(RestEndpointConfig.FEATURES_SUMMARY_ENDPOINT_URI, Feature[].class);
        featureSummary = Arrays.asList(features);
        HttpServletRequest rq = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (rq.getParameter("featureId") != null) {
            currentFeature = rt.getForObject(String.format(RestEndpointConfig.FEATURE_DETAIL_ENDPOINT_URI, rq.getParameter("featureId")), Feature.class);
        }
    }

    public void filterScenarios() {
        filter = true;
        System.out.println(" ----- "+scenarioTags +  " ===> "+String.format(RestEndpointConfig.FILTERED_SCENARIOS_ENDPOINT_URI, currentFeature.getId(), scenarioTags));

        RestTemplate rt = new RestTemplate();
        filteredScenarioText = rt.getForObject(String.format(RestEndpointConfig.FILTERED_SCENARIOS_ENDPOINT_URI, currentFeature.getId(), scenarioTags), String.class);
        System.out.println(filteredScenarioText);

    }

    public List<Feature> getFeatureSummary() {
        return featureSummary;
    }

    public void setFeatureSummary(List<Feature> featureSummary) {
        this.featureSummary = featureSummary;
    }

    public Feature getCurrentFeature() {
        return currentFeature;
    }

    public void setCurrentFeature(Feature currentFeature) {
        this.currentFeature = currentFeature;
    }

    public String getScenarioTags() {
        return scenarioTags;
    }

    public void setScenarioTags(String scenarioTags) {
        this.scenarioTags = scenarioTags;
    }

    public String getFilteredScenarioText() {
        return filteredScenarioText;
    }

    public void setFilteredScenarioText(String filteredScenarioText) {
        this.filteredScenarioText = filteredScenarioText;
    }

    public boolean isFilter() {
        return filter;
    }

    public void setFilter(boolean filter) {
        this.filter = filter;
    }
}
