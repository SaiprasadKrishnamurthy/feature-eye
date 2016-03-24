package com.tremlink.eui.ui;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 31/03/13
 * Time: 16:44
 * To change this template use File | Settings | File Templates.
 */
public class TransactionHistoryBean {
    private String id;
    private Date transactionDate;
    private String transactionType;
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd-MMM-yyyy HH:MM");



    public TransactionHistoryBean(String id, Date transactionDate, String transactionType) {
        this.id = id;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionDateString() {
        return FORMAT.format(getTransactionDate());
    }
}
