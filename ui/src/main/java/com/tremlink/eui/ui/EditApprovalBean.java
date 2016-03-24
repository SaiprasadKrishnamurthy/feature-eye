package com.tremlink.eui.ui;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 31/03/13
 * Time: 14:54
 * To change this template use File | Settings | File Templates.
 */
public class EditApprovalBean implements Serializable {

    private String approvalType;


    @PostConstruct
    public String init() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        System.out.println("-------POST CONSTRUCT------" + request.getParameter("docId"));
        return "";

    }
}
