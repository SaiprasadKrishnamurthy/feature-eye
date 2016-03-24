package com.tremlink.eui.ui;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultUploadedFile;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
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
public class EditSecurityDetailBean extends SecurityDetailBean implements Serializable {

    private UploadedFile currentUploadedFile;
    private DocumentDetailsBean selectedDocument;
    private String currentTransactionStatus;

    private List<TransactionHistoryBean> transactionHistory = new ArrayList<TransactionHistoryBean>();

    @PostConstruct
    public void init() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        System.out.println("-------POST CONSTRUCT------" + request.getParameter("secId"));
        setId(Long.parseLong(request.getParameter("secId")));
        setIssuer("Issuer " + request.getParameter("secId"));
        setCompanyCode("ABC002");
        setLeiCode("LEI Code " + (request.getParameter("secId")));
        setAmount("129899");
        setCurrencyCode("INR");

        for (int i = 0; i < 5; i++) {
            if (i % 2 == 0) {
                addDocument(new DocumentDetailsBean("Prospectus", "sample".getBytes(), "E1OXWW" + i, new Date(), UUID.randomUUID().toString()+".pdf"));
            } else {
                addDocument(new DocumentDetailsBean("Supplement", "sample".getBytes(), "CSEERS" + i, new Date(), UUID.randomUUID().toString()+".pdf"));
            }
        }

        for(int j=0; j<20; j++) {
            if(j%5 == 0) {
                transactionHistory.add(new TransactionHistoryBean(UUID.randomUUID().toString(), new Date(), "Approved"));
            }else{
                transactionHistory.add(new TransactionHistoryBean(UUID.randomUUID().toString(), new Date(), "Approval Cancelled"));

            }
        }
    }

    public void handleFileUpload(FileUploadEvent event) {
        System.out.println(" ------------- " + event.getFile().getFileName() + "\n\n\n\n");
        setCurrentUploadedFile(currentUploadedFile);
        FacesMessage msg = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void addDocument() {
        System.out.println("-----Add Doc--------------");
        addDocument(new DocumentDetailsBean(getCurrentDocumentType(), getCurrentUploadedFile(), getCurrentCOA(), getCurrentApprovedDate()));
        FacesMessage msg = new FacesMessage("Document added");
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public UploadedFile getCurrentUploadedFile() {
        return currentUploadedFile;
    }

    public void setCurrentUploadedFile(UploadedFile currentUploadedFile) {
        this.currentUploadedFile = currentUploadedFile;
    }

    public DocumentDetailsBean getSelectedDocument() {
        return selectedDocument;
    }

    public void setSelectedDocument(DocumentDetailsBean selectedDocument) {
        this.selectedDocument = selectedDocument;
    }

    public void onRowSelect(SelectEvent event) {
        System.out.println("Row selected --- " + event.getObject());
        selectedDocument = (DocumentDetailsBean) event.getObject();
        setCurrentCOA(selectedDocument.getCoa());
        setCurrentDocumentType(selectedDocument.getType());
        setCurrentApprovedDate(selectedDocument.getApprovedDate());
    }

    public String getCurrentTransactionStatus() {
        return currentTransactionStatus;
    }

    public void setCurrentTransactionStatus(String currentTransactionStatus) {
        this.currentTransactionStatus = currentTransactionStatus;
    }

    public List<TransactionHistoryBean> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(List<TransactionHistoryBean> transactionHistory) {
        this.transactionHistory = transactionHistory;
    }
}
