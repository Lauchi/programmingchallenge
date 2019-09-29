package application;

public class UpdateDocumentCommand {
    private String documentId;
    private String documentContent;
    private String documentType;

    public UpdateDocumentCommand(String documentId, String documentContent, String documentType) {
        this.documentId = documentId;
        this.documentContent = documentContent;
        this.documentType = documentType;
    }

    public String getDocumentContent() {
        return documentContent;
    }

    public String getDocumentType() {
        return documentType;
    }

    public String getDocumentId() {
        return documentId;
    }
}

