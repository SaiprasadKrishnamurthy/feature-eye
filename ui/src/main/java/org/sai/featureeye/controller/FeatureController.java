package org.sai.featureeye.controller;

import org.sai.featureeye.RestEndpointConfig;
import org.sai.featureeye.domain.Feature;
import org.springframework.web.client.RestTemplate;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by saipkri on 25/03/16.
 */
public class FeatureController {

    private List<Feature> featureSummary = new ArrayList<>();
    private List<Feature> featureSearchResults = new ArrayList<>();

    private Feature currentFeature;

    private String scenarioTags;

    private String filteredScenarioText;

    private boolean filter;

    private String featureText;

    private String tags;

    private String selectedTemplate;

    private String templateScenarioText;


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
        System.out.println(" ----- " + scenarioTags + " ===> " + String.format(RestEndpointConfig.FILTERED_SCENARIOS_ENDPOINT_URI, currentFeature.getId(), scenarioTags));

        RestTemplate rt = new RestTemplate();
        filteredScenarioText = rt.getForObject(String.format(RestEndpointConfig.FILTERED_SCENARIOS_ENDPOINT_URI, currentFeature.getId(), scenarioTags), String.class);
        System.out.println(filteredScenarioText);

    }

    public void searchFeatures() {
        RestTemplate rt = new RestTemplate();
        Feature[] searchResults = rt.getForObject(String.format(RestEndpointConfig.SEARCH_FEATURES_ENDPOINT_URI, tags, featureText), Feature[].class);
        System.out.println(Arrays.deepToString(searchResults));
        featureSearchResults = Arrays.asList(searchResults);
    }

    public void applyTemplate() {
        RestTemplate rt = new RestTemplate();
        System.out.println("Selected template: " + selectedTemplate);
        String id = featureSummary.stream().filter(f -> f.getFileName().equals(selectedTemplate)).findFirst().get().getId();
        Feature template = rt.getForObject(String.format(RestEndpointConfig.FEATURE_DETAIL_ENDPOINT_URI, id), Feature.class);
        templateScenarioText = template.getRawContents();
    }

    public void validate() {
        RestTemplate rt = new RestTemplate();
        String errorMsg = rt.postForObject(RestEndpointConfig.VALIDATE_FEATURE_ENDPOINT_URI, templateScenarioText, String.class);
        if (errorMsg == null || errorMsg.length() == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Feature looks good."));
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Validation errors: " + errorMsg));
        }
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

    public List<Feature> getFeatureSearchResults() {
        return featureSearchResults;
    }

    public void setFeatureSearchResults(List<Feature> featureSearchResults) {
        this.featureSearchResults = featureSearchResults;
    }

    public String getFeatureText() {
        return featureText;
    }

    public void setFeatureText(String featureText) {
        this.featureText = featureText;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getSelectedTemplate() {
        return selectedTemplate;
    }

    public void setSelectedTemplate(String selectedTemplate) {
        this.selectedTemplate = selectedTemplate;
    }

    public String getTemplateScenarioText() {
        return templateScenarioText;
    }

    public void setTemplateScenarioText(String templateScenarioText) {
        this.templateScenarioText = templateScenarioText;
    }
}
