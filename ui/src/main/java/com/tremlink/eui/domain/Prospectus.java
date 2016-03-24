package com.tremlink.eui.domain;

import org.primefaces.model.SelectableDataModel;

import javax.faces.model.ListDataModel;
import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 06/04/13
 * Time: 09:52
 * To change this template use File | Settings | File Templates.
 */
public class Prospectus extends ListDataModel<Prospectus> implements SelectableDataModel<Prospectus>,Serializable  {
    private String id;
    private String companyCode;
    private String issuerName;
    private String customField1;
    private String notificationStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public Object getRowKey(Prospectus prospectuses) {
        return prospectuses.getId();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Prospectus getRowData(String s) {
        List<Prospectus> cars = (List<Prospectus>) getWrappedData();
        System.out.println("----- get row data --------"+cars);
        for(Prospectus doc: cars) {
            if(doc.getId().equals(s)) {
                return doc;
            }
        }
        return null;
    }

    public String getCustomField1() {
        return customField1;
    }

    public void setCustomField1(String customField1) {
        this.customField1 = customField1;
    }

    public String getNotificationStatus() {
        return notificationStatus;
    }

    public void setNotificationStatus(String notificationStatus) {
        this.notificationStatus = notificationStatus;
    }
}
