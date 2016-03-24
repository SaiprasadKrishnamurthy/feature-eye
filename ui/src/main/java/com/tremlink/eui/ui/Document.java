package com.tremlink.eui.ui;

import com.tremlink.eui.domain.DocumentType;
import org.primefaces.model.UploadedFile;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 06/04/13
 * Time: 00:40
 * To change this template use File | Settings | File Templates.
 */
public class Document implements Serializable {

    private String fileName;
    private UploadedFile uploadedFile;
    private DocumentType type;
    private String label;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public DocumentType getType() {
        return type;
    }

    public void setType(DocumentType type) {
        this.type = type;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Document document = (Document) o;

        if (fileName != null ? !fileName.equals(document.fileName) : document.fileName != null) return false;
        if (type != document.type) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fileName != null ? fileName.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
