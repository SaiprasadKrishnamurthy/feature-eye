package com.tremlink.eui.ui;

import com.tremlink.eui.domain.Issuer;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.PostConstruct;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 26/03/13
 * Time: 23:12
 * To change this template use File | Settings | File Templates.
 */
public class RefDataBean {

    private Set<String> issuers = new TreeSet<String>();
    private Set<String> companyCodes = new TreeSet<String>();
    private Set<String> docTypes = new TreeSet<String>();
    private Set<String> securityTypes = new TreeSet<String>();
    private Set<String> approvalStatuses = new TreeSet<String>();
    private Set<String> approvalTypes = new TreeSet<String>();
    private Set<String> currencies = new TreeSet<String>();
    private Set<String> structureTypes = new TreeSet<String>();
    private Set<String> contentTypes = new TreeSet<String>();
    private Set<String> publicationDuration = new TreeSet<String>();
    private Set<String> leiCodes = new TreeSet<String>();
    private Set<String> countryCodes = new TreeSet<String>();
    private Set<String> transactionStatuses = new TreeSet<String>();
    private List<Issuer> allIssuers = new ArrayList<Issuer>();



    @PostConstruct
    public void init() {
       issuers.add("ABC Corporation");
       issuers.add("XYZ Inc");
       issuers.add("KSR Investments");

        companyCodes.add("ABC002");
        companyCodes.add("XYZ09X1");
        companyCodes.add("KSRW1X");

        docTypes.add("Prospectus");
        docTypes.add("Supplement");
        docTypes.add("Other");
        
        securityTypes.add("Shares");
        securityTypes.add("Debt");
        securityTypes.add("Asset Backed");
        securityTypes.add("Derivatives");
        securityTypes.add("Depositary Receipt");
        securityTypes.add("Closure Investment Funds");

        approvalStatuses.add("Approved");
        approvalStatuses.add("On Hold");
        approvalStatuses.add("Rejected");
        approvalStatuses.add("Approval Cancelled");
        
        currencies.add("GBP");
        currencies.add("USD");
        currencies.add("EUR");
        currencies.add("JPY");
        currencies.add("CHF");

        structureTypes.add("P1D - Prospectus Only");
        structureTypes.add("P2D - Prospectus & Summary Notes");
        structureTypes.add("P3D - Tripartite Prospectus");

        contentTypes.add("Prospectus");
        contentTypes.add("Base Prospectus");

        publicationDuration.add("12 Months");
        publicationDuration.add("24 Months");
        publicationDuration.add("Unlimited");

        leiCodes.add("GBIX123");
        leiCodes.add("GBLEIX123");
        leiCodes.add("DEIX123");
        leiCodes.add("FRIX123");

        countryCodes.add("United Kingdom");
        countryCodes.add("Germany");
        countryCodes.add("Norway");
        countryCodes.add("France");
        countryCodes.add("Italy");
        countryCodes.add("Spain");
        countryCodes.add("Switzerland");
        countryCodes.add("Poland");
        countryCodes.add("Belgium");
        countryCodes.add("Denmark");
        countryCodes.add("Sweden");
        countryCodes.add("Finland");

        transactionStatuses.add("Notified");
        transactionStatuses.add("Pending to be Notified");
        transactionStatuses.add("Notification Failed");

        approvalTypes.add("Prospectus");
        approvalTypes.add("Supplement");
        approvalTypes.add("COA");

        for(int i=0; i<30; i++) {
            allIssuers.add(new Issuer(i+"XZCJTP1",i+"LEISSXZ","ABC "+i+ " Inc"));
        }
    }

    public Set<String> getIssuers() {
        return issuers;
    }

    public void setIssuers(Set<String> issuers) {
        this.issuers = issuers;
    }

    public Set<String> getCompanyCodes() {
        return companyCodes;
    }

    public void setCompanyCodes(Set<String> companyCodes) {
        this.companyCodes = companyCodes;
    }

    public Set<String> getDocTypes() {
        return docTypes;
    }

    public void setDocTypes(Set<String> docTypes) {
        this.docTypes = docTypes;
    }

	public Set<String> getCurrencies() {
		return currencies;
	}

	public void setCurrencies(Set<String> currencies) {
		this.currencies = currencies;
	}

	public Set<String> getSecurityTypes() {
		return securityTypes;
	}

    public Set<String> getApprovalStatuses() {
        return approvalStatuses;
    }

    public Set<String> getStructureTypes() {
        return structureTypes;
    }

    public void setStructureTypes(Set<String> structureTypes) {
        this.structureTypes = structureTypes;
    }

    public Set<String> getContentTypes() {
        return contentTypes;
    }

    public Set<String> getPublicationDuration() {
        return publicationDuration;
    }

    public Set<String> getLeiCodes() {
        return leiCodes;
    }

    public void setLeiCodes(Set<String> leiCodes) {
        this.leiCodes = leiCodes;
    }

    public Set<String> getCountryCodes() {
        return countryCodes;
    }

    public void setCountryCodes(Set<String> countryCodes) {
        this.countryCodes = countryCodes;
    }

    public Set<String> getTransactionStatuses() {
        return transactionStatuses;
    }

    public void setTransactionStatuses(Set<String> transactionStatuses) {
        this.transactionStatuses = transactionStatuses;
    }

    public Set<String> getApprovalTypes() {
        return approvalTypes;
    }

    public void setApprovalTypes(Set<String> approvalTypes) {
        this.approvalTypes = approvalTypes;
    }

    public List<Issuer> getAllIssuers() {
        return allIssuers;
    }

    public void setAllIssuers(List<Issuer> allIssuers) {
        this.allIssuers = allIssuers;
    }
}
