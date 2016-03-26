package org.sai.featureeye.controller;

import org.primefaces.model.chart.*;
import org.sai.featureeye.RestEndpointConfig;
import org.sai.featureeye.gherkin.FeatureFileFragment;
import org.sai.featureeye.gherkin.Tag;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Stream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * Created by saipkri on 25/03/16.
 */
public class HomeController {

    private BarChartModel topSizedFeatures;
    private PieChartModel tagsBreakdown;


    public HomeController() {
        long limit = 10;
        RestTemplate rt = new RestTemplate();
        FeatureFileFragment[] features = rt.getForObject(RestEndpointConfig.FEATURE_MODELS_ENDPOINT_URI, FeatureFileFragment[].class);
        topSizedFeatures = new BarChartModel();

        List<FeatureFileFragment> topNFeatures = Stream.of(features)
                .sorted((f1, f2) -> Integer.valueOf(f2.getElements().size()).compareTo(Integer.valueOf(f1.getElements().size())))
                .limit(limit)
                .collect(toList());

        ChartSeries f = new ChartSeries();
        f.setLabel("Features");
        topNFeatures.forEach(fe -> f.set(fe.getAdditionalProperties().get("id"), fe.getElements().size()));
        topSizedFeatures.addSeries(f);
        topSizedFeatures.setAnimate(true);
        Axis xAxis = topSizedFeatures.getAxis(AxisType.X);
        xAxis.setTickAngle(-90);
        topSizedFeatures.setTitle("Top features (in terms of number of scenarios)");

        // Get all tags
        tagsBreakdown = new PieChartModel();
        List<String> allTags = Stream.of(features)
                .flatMap(f1 -> Stream.concat(Stream.of(nullSafeTags(f1.getTags())), f1.getElements().stream().flatMap(e1 -> Stream.of(nullSafeTags(e1.getTags())))))
                .map(Tag::getName)
                .collect(toList());
        Map<String, Long> tagsAndCounts = allTags.stream().collect(groupingBy(identity(), counting()));
        tagsAndCounts.forEach((a, b) -> tagsBreakdown.set(a, b));
        tagsBreakdown.setTitle("Tags usage in feature files");
        tagsBreakdown.setLegendPosition("e");
        tagsBreakdown.setShowDataLabels(true);
        tagsBreakdown.setShowDatatip(true);
        tagsBreakdown.setMouseoverHighlight(true);
    }

    private Tag[] nullSafeTags(final Tag[] tags) {
        return tags == null ? new Tag[0] : tags;
    }

    public BarChartModel getTopSizedFeatures() {
        return topSizedFeatures;
    }

    public void setTopSizedFeatures(BarChartModel topSizedFeatures) {
        this.topSizedFeatures = topSizedFeatures;
    }

    public PieChartModel getTagsBreakdown() {
        return tagsBreakdown;
    }

    public void setTagsBreakdown(PieChartModel tagsBreakdown) {
        this.tagsBreakdown = tagsBreakdown;
    }
}
