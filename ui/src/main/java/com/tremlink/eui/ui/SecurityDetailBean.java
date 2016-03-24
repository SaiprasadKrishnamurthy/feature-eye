package com.tremlink.eui.ui;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 26/03/13
 * Time: 23:12
 * To change this template use File | Settings | File Templates.
 */
public class SecurityDetailBean {
    private String issuer;
    private String leiCode;
    private String companyCode;
    private String currencyCode;
    private Long id;

    // flattened out for a simpler view.
    private List<String> documentTypes = new ArrayList<String>();
    private List<UploadedFile> files = new ArrayList<UploadedFile>();
    private List<Date> approvedDates = new ArrayList<Date>();

    private String currentDocumentType;
    private String currentCOA;
    private UploadedFile currentUploadedFile;
    private Date currentApprovedDate;
    private String securityAmount;
    private String securityType;

    private List<TransactionHistoryBean> transactionHistory = new ArrayList<TransactionHistoryBean>();
    private List<TransactionHistoryBean> filteredTransactionHistory ;
    private String amount;

    private List<DocumentDetailsBean> documents = new ArrayList<DocumentDetailsBean>();

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getLeiCode() {
        return leiCode;
    }

    public void setLeiCode(String leiCode) {
        this.leiCode = leiCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public void create() {
        System.out.println("Saved!");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Successful", "Document detail saved"));
    }

    public List<DocumentDetailsBean> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentDetailsBean> documents) {
        this.documents = documents;
    }

    public void handleFileUpload(FileUploadEvent event) {
        System.out.println(" ------------- "+event.getFile().getFileName()+"\n\n\n\n");
        currentUploadedFile = event.getFile();
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }


    public List<String> getDocumentTypes() {
        return documentTypes;
    }

    public void setDocumentTypes(List<String> documentTypes) {
        this.documentTypes = documentTypes;
    }

    public List<UploadedFile> getFiles() {
        return files;
    }

    public void setFiles(List<UploadedFile> files) {
        this.files = files;
    }

    public List<Date> getApprovedDates() {
        return approvedDates;
    }

    public void setApprovedDates(List<Date> approvedDates) {
        this.approvedDates = approvedDates;
    }

    public String getCurrentDocumentType() {
        return currentDocumentType;
    }

    public void setCurrentDocumentType(String currentDocumentType) {
        this.currentDocumentType = currentDocumentType;
    }

    public String getCurrentCOA() {
        return currentCOA;
    }

    public void setCurrentCOA(String currentCOA) {
        this.currentCOA = currentCOA;
    }

    public UploadedFile getCurrentUploadedFile() {
        return currentUploadedFile;
    }

    public void setCurrentUploadedFile(UploadedFile currentUploadedFile) {
        this.currentUploadedFile = currentUploadedFile;
    }


    public Date getCurrentApprovedDate() {
        return currentApprovedDate;
    }

    public void setCurrentApprovedDate(Date currentApprovedDate) {
        this.currentApprovedDate = currentApprovedDate;
    }

    public String getSecurityAmount() {
		return securityAmount;
	}

	public void setSecurityAmount(String securityAmount) {
		this.securityAmount = securityAmount;
	}

	public String getSecurityType() {
		return securityType;
	}

	public void setSecurityType(String securityType) {
		this.securityType = securityType;
	}

	public void addDocument(ActionEvent event) {
        this.documents.add(new DocumentDetailsBean(currentDocumentType, currentUploadedFile, currentCOA, currentApprovedDate));
        FacesMessage msg = new FacesMessage("Document details added successfully");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        System.out.println("Doc added+\n\n\n\n\n\n\n");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TransactionHistoryBean> getTransactionHistory() {
        return transactionHistory;
    }

    public void addTransactionHistory(TransactionHistoryBean transactionHistoryBean){
        this.transactionHistory.add(transactionHistoryBean);
    }

    public List<TransactionHistoryBean> getFilteredTransactionHistory() {
        return filteredTransactionHistory;
    }

    public void setFilteredTransactionHistory(List<TransactionHistoryBean> filteredTransactionHistory) {
        this.filteredTransactionHistory = filteredTransactionHistory;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void addDocument(DocumentDetailsBean documentDetailsBean) {
        documents.add(documentDetailsBean);
    }

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
}
