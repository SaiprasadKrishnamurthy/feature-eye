package com.tremlink.eui.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 06/04/13
 * Time: 13:01
 * To change this template use File | Settings | File Templates.
 */
public class SearchResultsBean implements Serializable {
    private String documentId;
    private String issuerCode;
    private String structureType;
    private String companyCode;
    private String issuerName;
    private String approvalStatus;
    private String notificationStatus;
    private Date approvalDate;
    private String leiCode;
    private String approvalType;
    private String approvalPage;
    private List<TransactionDetailBean> transactionDetailBeans = new ArrayList<TransactionDetailBean>();

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getIssuerCode() {
        return issuerCode;
    }

    public void setIssuerCode(String issuerCode) {
        this.issuerCode = issuerCode;
    }

    public String getStructureType() {
        return structureType;
    }

    public void setStructureType(String structureType) {
        this.structureType = structureType;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getIssuerName() {
        return issuerName;
    }

    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getNotificationStatus() {
        return notificationStatus;
    }

    public void setNotificationStatus(String notificationStatus) {
        this.notificationStatus = notificationStatus;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getLeiCode() {
        return leiCode;
    }

    public void setLeiCode(String leiCode) {
        this.leiCode = leiCode;
    }

    public List<TransactionDetailBean> getTransactionDetailBeans() {
        return transactionDetailBeans;
    }

    public void setTransactionDetailBeans(List<TransactionDetailBean> transactionDetailBeans) {
        this.transactionDetailBeans = transactionDetailBeans;
    }

    public void addTransaction(TransactionDetailBean transactionDetailBean) {
        this.transactionDetailBeans.add(transactionDetailBean);
    }

    public String getApprovalType() {
        return approvalType;
    }

    public void setApprovalType(String approvalType) {
        this.approvalType = approvalType;
    }

    public String getApprovalPage() {
        return approvalType+"ApprovalPage.do";
    }

    public void setApprovalPage(String approvalPage) {
        this.approvalPage = approvalPage;
    }
}
