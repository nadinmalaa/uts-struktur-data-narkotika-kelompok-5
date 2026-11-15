package model;

public class Provenance {
    private String caseId;
    private String sourceFile;
    private String sourceCourt;
    private String sourcePages;
    private String extractor;
    private String reviewer;
    private String extractionMethod;
    private String verificationStatus;

    public String getCaseId() {
        return this.caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getSourceFile() {
        return this.sourceFile;
    }

    public void setSourceFile(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    public String getSourceCourt() {
        return this.sourceCourt;
    }

    public void setSourceCourt(String sourceCourt) {
        this.sourceCourt = sourceCourt;
    }

    public String getSourcePages() {
        return this.sourcePages;
    }

    public void setSourcePages(String sourcePages) {
        this.sourcePages = sourcePages;
    }

    public String getExtractor() {
        return this.extractor;
    }

    public void setExtractor(String extractor) {
        this.extractor = extractor;
    }

    public String getReviewer() {
        return this.reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getExtractionMethod() {
        return this.extractionMethod;
    }

    public void setExtractionMethod(String extractionMethod) {
        this.extractionMethod = extractionMethod;
    }

    public String getVerificationStatus() {
        return this.verificationStatus;
    }

    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }
}
