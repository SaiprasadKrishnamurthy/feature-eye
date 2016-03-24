package com.tremlink.eui.ui;

import com.tremlink.eui.domain.DocumentType;
import com.tremlink.eui.domain.Prospectus;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 05/04/13
 * Time: 23:45
 * To change this template use File | Settings | File Templates.
 */
public class SearchApprovalBean implements Serializable {

    private String documentId;
    private List<String> issuerCodes;
    private List<String> structureTypes;
    private List<String> approvalTypes;
    private List<String> contentTypes;
    private List<String> companyCodes;
    private List<String> issuerNames;
    private List<String> leiCodes;
    private List<String> approvalStatuses;
    private List<String> notificationStatuses;
    private List<String> transactionStatuses;
    private Date approvalFromDate;
    private Date approvalToDate;
    private List<SearchResultsBean> results = new ArrayList<SearchResultsBean>();

    public SearchApprovalBean() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if(request.getParameter("approvalStatus") != null) {
            search();
        }
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public List<String> getIssuerCodes() {
        return issuerCodes;
    }

    public void setIssuerCodes(List<String> issuerCodes) {
        this.issuerCodes = issuerCodes;
    }

    public List<String> getStructureTypes() {
        return structureTypes;
    }

    public void setStructureTypes(List<String> structureTypes) {
        this.structureTypes = structureTypes;
    }

    public List<String> getContentTypes() {
        return contentTypes;
    }

    public void setContentTypes(List<String> contentTypes) {
        this.contentTypes = contentTypes;
    }

    public List<String> getCompanyCodes() {
        return companyCodes;
    }

    public void setCompanyCodes(List<String> companyCodes) {
        this.companyCodes = companyCodes;
    }

    public List<String> getIssuerNames() {
        return issuerNames;
    }

    public void setIssuerNames(List<String> issuerNames) {
        this.issuerNames = issuerNames;
    }

    public List<String> getLeiCodes() {
        return leiCodes;
    }

    public void setLeiCodes(List<String> leiCodes) {
        this.leiCodes = leiCodes;
    }

    public List<String> getApprovalStatuses() {
        return approvalStatuses;
    }

    public void setApprovalStatuses(List<String> approvalStatuses) {
        this.approvalStatuses = approvalStatuses;
    }

    public List<String> getNotificationStatuses() {
        return notificationStatuses;
    }

    public void setNotificationStatuses(List<String> notificationStatuses) {
        this.notificationStatuses = notificationStatuses;
    }

    public Date getApprovalFromDate() {
        return approvalFromDate;
    }

    public void setApprovalFromDate(Date approvalFromDate) {
        this.approvalFromDate = approvalFromDate;
    }

    public Date getApprovalToDate() {
        return approvalToDate;
    }

    public void setApprovalToDate(Date approvalToDate) {
        this.approvalToDate = approvalToDate;
    }

    public List<String> getTransactionStatuses() {
        return transactionStatuses;
    }

    public void setTransactionStatuses(List<String> transactionStatuses) {
        this.transactionStatuses = transactionStatuses;
    }

    public void edit(ActionEvent event) throws IOException {
        String documentId = (String) event.getComponent().getAttributes().get("docId");
        String approvalType = (String) event.getComponent().getAttributes().get("approvalType");
        System.out.println(" ----- Entered Edit ---- id: "+documentId+" ------ "+approvalType+"ApprovalPage.do?faces-redirect=true&docId="+documentId);
        String url = approvalType+"ApprovalPage.do?faces-redirect=true&docId="+documentId;
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.sendRedirect(url);

    }

    public void search() {
        results.clear();
        System.out.println(" ------------ Search ------------ ");

        for (int i = 0; i < 50; i++) {
            SearchResultsBean result = new SearchResultsBean();
            result.setCompanyCode("GBX012" + i);
            if (i % 3 == 0) result.setApprovalStatus("Approved");
            if (i % 5 == 0) result.setApprovalStatus("Approval Pending");
            if (i % 15 == 0) result.setApprovalStatus("Approval Cancelled");

            if (i % 2 == 0) result.setApprovalType("Prospectus");
            if (i % 3 == 0) result.setApprovalType("COA");
            if (i % 5 == 0) result.setApprovalType("Supplement");


            result.setDocumentId(UUID.randomUUID().toString());
            result.setIssuerCode("GBSAG" +i);
            result.setIssuerName(i + " Labs & Consulting");
            if (i % 3 == 0) result.setStructureType("P1D - Prospectus Only");
            if (i % 5 == 0) result.setStructureType("P2D - Prospectus & Summary Notes");
            if (i % 7 == 0) result.setStructureType("P3D - Tripartite Prospectus");
            if (i % 3 == 0) result.setNotificationStatus("Notified");
            if (i % 3 == 0) result.setApprovalDate(new Date());
            result.setLeiCode("GBX109"+i);
            results.add(result);

            for(int j=0; j<15; j++) {
                TransactionDetailBean transactionDetailBean = new TransactionDetailBean();
                transactionDetailBean.setTransactionId(UUID.randomUUID().toString());
                transactionDetailBean.setProspectusId("GBPROS"+j);
                transactionDetailBean.setSupplementId("GBSUPP"+j);
                transactionDetailBean.setCoaId("GBCOA"+j);
                transactionDetailBean.setDate("22-Mar-2010");
                if(j%3 == 0) transactionDetailBean.setStatus("Notified");
                if(j%3 == 2) transactionDetailBean.setStatus("Notification Pending");
                result.addTransaction(transactionDetailBean);
            }

        }
    }

    public List<SearchResultsBean> getResults() {
        return results;
    }

    public void setResults(List<SearchResultsBean> results) {
        this.results = results;
    }

    public List<String> getApprovalTypes() {
        return approvalTypes;
    }

    public void setApprovalTypes(List<String> approvalTypes) {
        this.approvalTypes = approvalTypes;
    }
}
