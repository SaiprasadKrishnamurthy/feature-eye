package com.tremlink.eui.ui;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;

public class CreateSecurityDetailWizard {  
	  
      
    private boolean skip; 
    private String currentDocumentType;
    private String currentCOA;
    private UploadedFile currentUploadedFile;
    private Date currentApprovedDate;
    private String securityAmount;
    private String securityType;
    private List<DocumentDetailsBean> documents = new ArrayList<DocumentDetailsBean>();
    private DocumentDetailsBean selectedDocument;
    private boolean documentSelected;
    private String currentIssuer;
    private String currentLeiCode;
    private String currentAmount;
    private String currentCompanyCode;
    private String currencyCode;


    private boolean documentPresent;
      
    public void save(ActionEvent actionEvent) {  
        FacesMessage msg = new FacesMessage("Successful", "Saved");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
      
    public boolean isSkip() {  
        return skip;  
    }  
  
    public void setSkip(boolean skip) {  
        this.skip = skip;  
    }  
      
    public String onFlowProcess(FlowEvent event) {  
        System.out.println("Current wizard step:" + event.getOldStep());  
        System.out.println("Next step:" + event.getNewStep());  
          
        if(skip) {  
            skip = false;     
            return "confirm";  
        }  
        else {  
            return event.getNewStep();  
        }  
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

	public List<DocumentDetailsBean> getDocuments() {
		return documents;
	}

	public void setDocuments(List<DocumentDetailsBean> documents) {
		this.documents = documents;
	}
	
	public void onRowSelect(SelectEvent event) {  
        System.out.println("Row selected --- "+event.getSource());
		documentSelected = true;
    }  
  
	public DocumentDetailsBean getSelectedDocument() {
		return selectedDocument;
	}

	public void setSelectedDocument(DocumentDetailsBean selectedDocument) {
		this.selectedDocument = selectedDocument;
	}
    
	 public void handleFileUpload(FileUploadEvent event) {
	        System.out.println(" ------------- "+event.getFile().getFileName()+"\n\n\n\n");
	        currentUploadedFile = event.getFile();
	        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
	 
	 public void addDocument() {
		 System.out.println("-----Add Doc--------------");
		 this.documents.add(new DocumentDetailsBean(currentDocumentType, currentUploadedFile, currentCOA, currentApprovedDate));
		 FacesMessage msg = new FacesMessage("Document added");  
	     FacesContext.getCurrentInstance().addMessage(null, msg);
         documentPresent = true;
		 
	 }
	 
	 public void deleteDocument() {
		 System.out.println("-----Delete Doc--------------"+selectedDocument);
		 FacesMessage msg = new FacesMessage("Document deleted");
		 FacesContext.getCurrentInstance().addMessage(null, msg);
		 int index = documents.indexOf(selectedDocument);
		 if(index != -1) {
			 documents.remove(index);
		 }
		 System.out.println("-----Deleted index--------------"+index);
         if(documents.isEmpty()) {
             documentPresent = false;
         }
			 
	 }

	public boolean isDocumentSelected() {
		return documentSelected;
	}

	public void setDocumentSelected(boolean documentSelected) {
		this.documentSelected = documentSelected;
	}

	public String getCurrentIssuer() {
		return currentIssuer;
	}

	public void setCurrentIssuer(String currentIssuer) {
		this.currentIssuer = currentIssuer;
	}

	public String getCurrentLeiCode() {
		return currentLeiCode;
	}

	public void setCurrentLeiCode(String currentLeiCode) {
		this.currentLeiCode = currentLeiCode;
	}

	public String getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(String currentAmount) {
		this.currentAmount = currentAmount;
	}

	public String getCurrentCompanyCode() {
		return currentCompanyCode;
	}

	public void setCurrentCompanyCode(String currentCompanyCode) {
		this.currentCompanyCode = currentCompanyCode;
	}
	
	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public void create() throws IOException{
		System.out.println("Save entered ----- ");
		FacesMessage msg = new FacesMessage("Document saved");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		FacesContext.getCurrentInstance().getExternalContext().redirect("createPage.do");
	}

    public void deleteSelectedDocument(ActionEvent event) {
        String param = (String) event.getComponent().getAttributes().get("parameter");
        System.out.println(" ------------- PARAM -----> "+param);
        DocumentDetailsBean lookedUp = null;
        for(DocumentDetailsBean doc: documents) {
            if(param.equals(doc.getFile().getFileName())) {
                  lookedUp = doc;
                break;
            }
        }

        if(lookedUp != null) {
            documents.remove(lookedUp);
        }

    }
    public boolean isDocumentPresent() {
        return documentPresent;
    }
}
