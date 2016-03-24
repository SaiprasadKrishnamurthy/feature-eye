package org.sai.featureeye.gherkin;

/**
 * Created by saikris on 24/03/2016.
 */
public class Tag {
    private String name;
    private int line;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "name='" + name + '\'' +
                ", line=" + line +
                '}';
    }
}
