package com.tremlink.eui.ui;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 06/04/13
 * Time: 13:01
 * To change this template use File | Settings | File Templates.
 */
public class TransactionDetailBean implements Serializable {
    private String transactionId;
    private String prospectusId;
    private String supplementId;
    private String coaId;
    private String date;
    private String status;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getProspectusId() {
        return prospectusId;
    }

    public void setProspectusId(String prospectusId) {
        this.prospectusId = prospectusId;
    }

    public String getSupplementId() {
        return supplementId;
    }

    public void setSupplementId(String supplementId) {
        this.supplementId = supplementId;
    }

    public String getCoaId() {
        return coaId;
    }

    public void setCoaId(String coaId) {
        this.coaId = coaId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
