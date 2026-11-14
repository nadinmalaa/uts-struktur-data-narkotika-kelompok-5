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

    // Getter dan Setter
    public String getCaseId() { return caseId; }
    public void setCaseId(String caseId) { this.caseId = caseId; }

    public String getSourceFile() { return sourceFile; }
    public void setSourceFile(String sourceFile) { this.sourceFile = sourceFile; }

    public String getSourceCourt() { return sourceCourt; }
    public void setSourceCourt(String sourceCourt) { this.sourceCourt = sourceCourt; }

    public String getSourcePages() { return sourcePages; }
    public void setSourcePages(String sourcePages) { this.sourcePages = sourcePages; }

    public String getExtractor() { return extractor; }
    public void setExtractor(String extractor) { this.extractor = extractor; }

    public String getReviewer() { return reviewer; }
    public void setReviewer(String reviewer) { this.reviewer = reviewer; }

    public String getExtractionMethod() { return extractionMethod; }
    public void setExtractionMethod(String extractionMethod) { this.extractionMethod = extractionMethod; }

    public String getVerificationStatus() { return verificationStatus; }
    public void setVerificationStatus(String verificationStatus) { this.verificationStatus = verificationStatus; }
}