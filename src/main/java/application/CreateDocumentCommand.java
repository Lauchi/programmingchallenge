package application;

public class CreateDocumentCommand {
    private String documentId;
    private String content;
    private String type;

    public CreateDocumentCommand(String documentId, String content, String documentType) {
        this.documentId = documentId;
        this.content = content;
        this.type = documentType;
    }

    public String getContent() {
        return content;
    }

    public String getDocumentType() {
        return type;
    }

    public String getDocumentId() {
        return documentId;
    }
}
