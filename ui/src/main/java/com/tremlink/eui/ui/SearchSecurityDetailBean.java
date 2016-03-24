package com.tremlink.eui.ui;

import javax.faces.event.ActionEvent;
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
public class SearchSecurityDetailBean implements Serializable {

    private List<String> issuerCode;
    private List<String> companyCode;
    private String transactionId;
    private String approvalDateFrom;
    private String approvalDateTo;
    private String leiCode;
    private String currencyCode;
    private List<SecurityDetailBean> securityDetails = new ArrayList<SecurityDetailBean>();
    private List<SecurityDetailBean> filteredSecurityDetails;
    private List<String> approvalStatus = new ArrayList<String>();


    public List<String> getIssuerCode() {
        return issuerCode;
    }

    public void setIssuerCode(List<String> issuerCode) {
        this.issuerCode = issuerCode;
    }

    public List<String> getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(List<String> companyCode) {
        this.companyCode = companyCode;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getApprovalDateFrom() {
        return approvalDateFrom;
    }

    public void setApprovalDateFrom(String approvalDateFrom) {
        this.approvalDateFrom = approvalDateFrom;
    }

    public String getApprovalDateTo() {
        return approvalDateTo;
    }

    public void setApprovalDateTo(String approvalDateTo) {
        this.approvalDateTo = approvalDateTo;
    }

    public String getLeiCode() {
        return leiCode;
    }

    public void setLeiCode(String leiCode) {
        this.leiCode = leiCode;
    }

    public void search() {
        System.out.println(" ------------- SEARCH PERFORMED -------");

        String[] transactionTypes = {"Approved", "Revoked"};

        securityDetails.clear();

        // sample data.
        for(int i=1; i<31; i++) {
            SecurityDetailBean bean = new SecurityDetailBean();
            bean.setId(Long.parseLong(i + ""));
            bean.setIssuer("Issuer "+ i);
            bean.setCompanyCode("Company code "+i);
            bean.setLeiCode(UUID.randomUUID().toString());
            securityDetails.add(bean);

            for(int j=0; j<20; j++) {
                if(j%5 == 0) {
                    bean.addTransactionHistory(new TransactionHistoryBean(UUID.randomUUID().toString(), new Date(), "Approved"));
                }else{
                    bean.addTransactionHistory(new TransactionHistoryBean(UUID.randomUUID().toString(), new Date(), "Revoked"));

                }
            }
        }

    }

    public List<SecurityDetailBean> getSecurityDetails() {
        return securityDetails;
    }

    public List<SecurityDetailBean> getFilteredSecurityDetails() {
        return filteredSecurityDetails;
    }

    public void setFilteredSecurityDetails(List<SecurityDetailBean> filteredSecurityDetails) {
        this.filteredSecurityDetails = filteredSecurityDetails;
    }

    public String edit(ActionEvent event) {
        Long id = (Long) event.getComponent().getAttributes().get("param");
        System.out.println(" ----- Entered Edit ---- id: "+id);
        return "editPage.do?faces-redirect=true";
    }

    public List<String> getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(List<String> approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
}
