package com.tremlink.eui.ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.model.ListDataModel;

import org.primefaces.model.SelectableDataModel;
import org.primefaces.model.UploadedFile;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 26/03/13
 * Time: 23:12
 * To change this template use File | Settings | File Templates.
 */
public class DocumentDetailsBean extends ListDataModel<DocumentDetailsBean> implements SelectableDataModel<DocumentDetailsBean>{

    private final String type ;
    private UploadedFile file;
    private final String coa;
    private final Date approvedDate;
    private String approvedDateAsString;
    private byte[] fileContents;
    private String fileName;

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("dd-MMM-yyyy");

    public DocumentDetailsBean(String type, UploadedFile file, String coa, Date approvedDate) {
        this.type = type;
        this.file = file;
        this.coa = coa;
        this.approvedDate = approvedDate;
    }

    public DocumentDetailsBean(String type, byte[] fileContents, String coa, Date approvedDate, String fileName) {
        this.type = type;
        this.fileContents = fileContents;
        this.coa = coa;
        this.approvedDate = approvedDate;
        this.fileName = fileName;
    }


    public UploadedFile getFile() {
        return file;
    }

    public Date getApprovedDate() {
        return approvedDate;
    }

    public String getType() {
        return type;
    }

    public String getCoa() {
        return coa;
    }

    public String getApprovedDateAsString() {
        return FORMAT.format(approvedDate);
    }


	@Override
	public DocumentDetailsBean getRowData(final String key) {
		List<DocumentDetailsBean> cars = (List<DocumentDetailsBean>) getWrappedData();
		System.out.println("----- get row data --------"+cars);
		for(DocumentDetailsBean doc: cars) {
			if(doc.getFile().getFileName().equals(key)) {
				return doc;
			}
		}
		return null;
	}


	@Override
	public Object getRowKey(DocumentDetailsBean doc) {
		return doc.getFile().getFileName();
	}


	@Override
	public int hashCode() {
		return this.fileName.hashCode();
	}


	@Override
	public boolean equals(Object obj) {
		if(obj instanceof DocumentDetailsBean) {
			return this.getFileName().equals(((DocumentDetailsBean)obj).getFileName());
		}
		return false;
	}

    public byte[] getFileContents() {
        return fileContents;
    }

    public void setFileContents(byte[] fileContents) {
        this.fileContents = fileContents;
    }

    public String getFileName() {
        if(file != null) {
            return file.getFileName();
        }
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
