package com.tremlink.eui.ui;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by saikris on 19/03/2016.
 */
public class CreateRepoController {

    private String dummy = "";
    private Date createdDate;

    private List<String> fields = Arrays.asList("trash", "trash", "plusthick");

    public CreateRepoController(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "No search rules defined for this repository. You need to define search rules for this repository to be searchable using fuzzy filters."));
    }

    public String getDummy() {
        return dummy;
    }

    public void setDummy(String dummy) {
        this.dummy = dummy;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
