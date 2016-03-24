package org.sai.featureeye.scratchpad;

import com.fasterxml.jackson.databind.ObjectMapper;
import gherkin.formatter.JSONFormatter;
import gherkin.parser.Parser;
import gherkin.util.FixJava;
import org.sai.featureeye.gherkin.FeatureFileFragment;

import java.io.*;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.util.stream.Collectors.toList;

/**
 * Created by saikris on 24/03/2016.
 */
public class GherkinUtil {

    public static void main(String[] args) throws Exception {

        InputStreamReader in = null;
        in = new InputStreamReader(new FileInputStream("example.feature"), "UTF-8");
        String gherkin = FixJava.readReader(in);
        StringBuilder json = new StringBuilder();
        JSONFormatter formatter = new JSONFormatter(json);
        Parser parser = new Parser(formatter);
        parser.parse(gherkin, "", 0);
        formatter.done();
        formatter.close();

        System.out.println(json);


        ObjectMapper mapper = new ObjectMapper();
        FeatureFileFragment[] frag = mapper.readValue(json.toString(), FeatureFileFragment[].class);
        System.out.println(frag.length);
        frag[0].getElements().forEach(element -> System.out.println(element.getKeyword()+" ==> "+ Arrays.deepToString(element.getTags())+" === "+element.getSteps().stream().map(step -> step.getName()).collect(toList())));

        System.out.println(Arrays.deepToString(frag[0].getTags()));

    }
}
