package com.tremlink.eui.ui;

import com.tremlink.eui.domain.DocumentType;
import com.tremlink.eui.domain.Prospectus;
import com.tremlink.eui.domain.Supplement;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 05/04/13
 * Time: 23:45
 * To change this template use File | Settings | File Templates.
 */
public class COAApprovalBean extends ProspectusApprovalBean implements Serializable {

    private String structureType;
    private String contentType;
    private String publicationDuration;
    private String companyCode;
    private Date approvalDate;
    private String leiCode;
    private String issuerName;
    private boolean showSupplement = true;
    private boolean showSummaryNote;
    private boolean showRegistrationDocument;
    private boolean showSecuritiesNoteDocument;
    private List<Document> documents = new ArrayList<Document>();
    private UploadedFile prospectus;
    private UploadedFile supplement;
    private UploadedFile summaryNote;
    private UploadedFile regDoc;
    private UploadedFile secNoteDoc;
    private String currency;
    private String securityType;
    private String amount;
    private String prospectusId;
    private List<Prospectus> prospectuses = new ArrayList<Prospectus>();
    private List<Supplement> supplements = new ArrayList<Supplement>();
    private List<Prospectus> filteredProspectuses = new ArrayList<Prospectus>();
    private List<Supplement> filteredSupplements = new ArrayList<Supplement>();
    private List<String> passportedCountries = new ArrayList<String>();
    private String requestingCountry;
    private Date documentDate;

    private Prospectus selectedProspectus;

    private UploadedFile file;
    private Supplement selectedSupplement;


    {
        Random random = new Random();
        for (int i = 0; i < 50; i++) {
            Prospectus _prospectus = new Prospectus();
            _prospectus.setId(UUID.randomUUID().toString());
            _prospectus.setCompanyCode("GBCCX" + i);
            _prospectus.setIssuerName(i + " - Investments Inc");
            _prospectus.setCustomField1("Custom field 1: "+i);
            _prospectus.setNotificationStatus("Draft");
            prospectuses.add(_prospectus);
        }

        for (int i = 0; i < 50; i++) {
            Supplement _supplement = new Supplement();
            _supplement.setId(UUID.randomUUID().toString());
            _supplement.setCompanyCode("GBCCX" + i);
            _supplement.setIssuerName(i + " - Investments Inc");
            supplements.add(_supplement);
        }
    }


    public void handleStructureTypeChange() {
        showRegistrationDocument = false;
        showSecuritiesNoteDocument = false;
        showSummaryNote = false;

        documents.clear();
        System.out.println(" ------------- " + structureType);
        Document document = new Document();
        document.setFileName("");
        document.setType(DocumentType.Prospectus);
        document.setLabel("Prospectus");
        documents.add(document);

        if (structureType.equals("P3D - Tripartite Prospectus")) {
            document = new Document();
            document.setFileName("");
            document.setType(DocumentType.SummaryNote);
            document.setLabel("Summary Note");
            documents.add(document);

            document = new Document();
            document.setFileName("");
            document.setType(DocumentType.RegistrationDocument);
            document.setLabel("Registration Document");
            documents.add(document);

            document = new Document();
            document.setFileName("");
            document.setType(DocumentType.SecuritiesNote);
            document.setLabel("Securities Note");
            documents.add(document);

            showRegistrationDocument = true;
            showSecuritiesNoteDocument = true;
            showSummaryNote = true;


        }

        if (structureType.equals("P2D - Prospectus & Summary Notes")) {
            document = new Document();
            document.setFileName("");
            document.setType(DocumentType.SummaryNote);
            document.setLabel("Summary Note");
            documents.add(document);

            document = new Document();
            document.setFileName("");
            document.setType(DocumentType.SecuritiesNote);
            document.setLabel("Securities Note");
            documents.add(document);

            showSecuritiesNoteDocument = true;
            showSummaryNote = true;

        }

    }

    public void deleteSelectedDocument(ActionEvent event) {
        DocumentType documentType = (DocumentType) event.getComponent().getAttributes().get("documentType");
        String fileName = (String) event.getComponent().getAttributes().get("fileName");

        Document toFind = new Document();
        toFind.setFileName(fileName);
        toFind.setType(documentType);

        documents.remove(toFind);

        FacesMessage msg = new FacesMessage("Document deleted");
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

    public void handleProspectusUpload(FileUploadEvent event) {
        System.out.println(" ------------- " + event.getFile().getFileName() + "\n\n\n\n");
        prospectus = event.getFile();
    }

    public void handleSupplementUpload(FileUploadEvent event) {
        System.out.println(" ------------- " + event.getFile().getFileName() + "\n\n\n\n");
        supplement = event.getFile();
    }

    public void viewSupplement() {
        System.out.println(" ----------- View Supplement----------------- ");
    }

    public void deleteSupplement() {
        System.out.println(" ----------- Delete Supplement ----------------- ");
        supplement = null;
    }


    public void handleSummaryNoteUpload(FileUploadEvent event) {
        System.out.println(" ------------- " + event.getFile().getFileName() + "\n\n\n\n");
        summaryNote = event.getFile();
    }

    public void handleRegistrationDocumentUpload(FileUploadEvent event) {
        System.out.println(" ------------- " + event.getFile().getFileName() + "\n\n\n\n");
        regDoc = event.getFile();
    }

    public void handleSecuritiesNoteDocumentUpload(FileUploadEvent event) {
        System.out.println(" ------------- " + event.getFile().getFileName() + "\n\n\n\n");
        secNoteDoc = event.getFile();
    }

    public void viewProspectus() {
        System.out.println(" ----------- View Prospectus ----------------- ");
    }

    public void deleteProspectus() {
        System.out.println(" ----------- Delete Prospectus ----------------- ");
        prospectus = null;
    }

    public void viewRegistrationDocument() {
        System.out.println(" ----------- View RegistrationDocument----------------- ");
    }

    public void deleteRegistrationDocument() {
        System.out.println(" ----------- Delete RegistrationDocument ----------------- ");
        regDoc = null;
    }

    public void viewSummaryNoteDocument() {
        System.out.println(" ----------- View SummaryNoteDocument ----------------- ");
        summaryNote = null;
    }

    public void deleteSummaryNoteDocument() {
        System.out.println(" ----------- Delete SummaryNoteDocument ----------------- ");
        summaryNote = null;
    }

    public void viewSecuritiesNoteDocument() {
        System.out.println(" ----------- View SecuritiesNoteDocument ----------------- ");
    }

    public void deleteSecuritiesNoteDocument() {
        System.out.println(" ----------- Delete SecuritiesNoteDocument ----------------- ");
        secNoteDoc = null;
    }

    public void save() {
        FacesMessage msg = new FacesMessage("Successful", "COA Approval Saved.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String getStructureType() {
        return structureType;
    }

    public void setStructureType(String structureType) {
        this.structureType = structureType;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getPublicationDuration() {
        return publicationDuration;
    }

    public void setPublicationDuration(String publicationDuration) {
        this.publicationDuration = publicationDuration;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getLeiCode() {
        return leiCode;
    }

    public void setLeiCode(String leiCode) {
        this.leiCode = leiCode;
    }

    public String getIssuerName() {
        return issuerName;
    }

    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }

    public boolean isShowSummaryNote() {
        return showSummaryNote;
    }

    public void setShowSummaryNote(boolean showSummaryNote) {
        this.showSummaryNote = showSummaryNote;
    }

    public boolean isShowRegistrationDocument() {
        return showRegistrationDocument;
    }

    public void setShowRegistrationDocument(boolean showRegistrationDocument) {
        this.showRegistrationDocument = showRegistrationDocument;
    }

    public boolean isShowSecuritiesNoteDocument() {
        return showSecuritiesNoteDocument;
    }

    public void setShowSecuritiesNoteDocument(boolean showSecuritiesNoteDocument) {
        this.showSecuritiesNoteDocument = showSecuritiesNoteDocument;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public UploadedFile getProspectus() {
        return prospectus;
    }

    public void setProspectus(UploadedFile prospectus) {
        this.prospectus = prospectus;
    }

    public UploadedFile getSummaryNote() {
        return summaryNote;
    }

    public void setSummaryNote(UploadedFile summaryNote) {
        this.summaryNote = summaryNote;
    }

    public UploadedFile getRegDoc() {
        return regDoc;
    }

    public void setRegDoc(UploadedFile regDoc) {
        this.regDoc = regDoc;
    }

    public UploadedFile getSecNoteDoc() {
        return secNoteDoc;
    }

    public void setSecNoteDoc(UploadedFile secNoteDoc) {
        this.secNoteDoc = secNoteDoc;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSecurityType() {
        return securityType;
    }

    public void setSecurityType(String securityType) {
        this.securityType = securityType;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getProspectusId() {
        return prospectusId;
    }

    public void setProspectusId(String prospectusId) {
        this.prospectusId = prospectusId;
    }

    public List<Prospectus> getProspectuses() {
        return prospectuses;
    }

    public void setProspectuses(List<Prospectus> prospectuses) {
        this.prospectuses = prospectuses;
    }

    public List<Prospectus> getFilteredProspectuses() {
        return filteredProspectuses;
    }

    public void setFilteredProspectuses(List<Prospectus> filteredProspectuses) {
        this.filteredProspectuses = filteredProspectuses;
    }

    public Prospectus getSelectedProspectus() {
        return selectedProspectus;
    }

    public void setSelectedProspectus(Prospectus selectedProspectus) {
        this.selectedProspectus = selectedProspectus;
    }

    public void onRowSelect(SelectEvent event) {
        selectedProspectus = (Prospectus) event.getObject();
    }

    public void onSupplementRowSelect(SelectEvent event) {
        selectedSupplement = (Supplement) event.getObject();
    }

    public boolean isShowSupplement() {
        return showSupplement;
    }

    public void setShowSupplement(boolean showSupplement) {
        this.showSupplement = showSupplement;
    }

    public UploadedFile getSupplement() {
        return supplement;
    }

    public void setSupplement(UploadedFile supplement) {
        this.supplement = supplement;
    }

    public Date getDocumentDate() {
        return documentDate;
    }

    public void setDocumentDate(Date documentDate) {
        this.documentDate = documentDate;
    }

    public List<Supplement> getSupplements() {
        return supplements;
    }

    public void setSupplements(List<Supplement> supplements) {
        this.supplements = supplements;
    }

    public List<Supplement> getFilteredSupplements() {
        return filteredSupplements;
    }

    public void setFilteredSupplements(List<Supplement> filteredSupplements) {
        this.filteredSupplements = filteredSupplements;
    }

    public Supplement getSelectedSupplement() {
        return selectedSupplement;
    }

    public void setSelectedSupplement(Supplement selectedSupplement) {
        this.selectedSupplement = selectedSupplement;
    }

    public List<String> getPassportedCountries() {
        return passportedCountries;
    }

    public void setPassportedCountries(List<String> passportedCountries) {
        this.passportedCountries = passportedCountries;
    }

    public String getRequestingCountry() {
        return requestingCountry;
    }

    public void setRequestingCountry(String requestingCountry) {
        this.requestingCountry = requestingCountry;
    }
}
