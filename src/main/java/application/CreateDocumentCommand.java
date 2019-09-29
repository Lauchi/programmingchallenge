package application;

import java.util.UUID;

public class CreateDocumentCommand {
    private UUID documentId;
    private String content;
    private String type;

    public CreateDocumentCommand(UUID documentId, String content, String documentType) {
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

    public UUID getDocumentId() {
        return documentId;
    }
}
