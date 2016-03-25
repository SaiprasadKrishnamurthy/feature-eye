package org.sai.featureeye.controller;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.sai.featureeye.RestEndpointConfig;
import org.sai.featureeye.domain.Feature;
import org.sai.featureeye.gherkin.FeatureFileFragment;
import org.springframework.web.client.RestTemplate;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by saipkri on 25/03/16.
 */
public class HomeController {

    private BarChartModel topSizedFeatures;


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
    }

    public BarChartModel getTopSizedFeatures() {
        return topSizedFeatures;
    }

    public void setTopSizedFeatures(BarChartModel topSizedFeatures) {
        this.topSizedFeatures = topSizedFeatures;
    }
}
