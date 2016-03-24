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
public class Supplement extends ListDataModel<Supplement> implements SelectableDataModel<Supplement>,Serializable  {
    private String id;
    private String companyCode;
    private String issuerName;

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
    public Object getRowKey(Supplement prospectuses) {
        return prospectuses.getId();  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Supplement getRowData(String s) {
        List<Supplement> cars = (List<Supplement>) getWrappedData();
        System.out.println("----- get row data --------"+cars);
        for(Supplement doc: cars) {
            if(doc.getId().equals(s)) {
                return doc;
            }
        }
        return null;
    }
}
