package com.tremlink.eui.ui;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by saikris on 19/03/2016.
 */
public class BackupController {

    private String backup = "";
    private Date createdDate;

    private List<String> fields = Arrays.asList("trash", "trash", "plusthick");

    public BackupController() {
    }

    public String getBackup() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "This is an expensive and time consuming operation."));
        return backup;
    }

    public void setBackup(String backpu) {
        this.backup = backpu;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }
}
