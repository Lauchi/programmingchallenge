package domain.documents;

import domain.ValidationResult;

import java.util.UUID;

public class Document {
    private UUID documentId;
    private String content;
    private DocumentType documentType;

    public Document(UUID documentId, String content, DocumentType documentType) {
        this.documentId = documentId;
        this.content = content;
        this.documentType = documentType;
    }

    public static ValidationResult<Document> Create(UUID documentId, String content, DocumentType documentType) {
        return new ValidationResult(new Document(documentId, content, documentType));
    }

    public UUID getDocumentId() {
        return documentId;
    }

    public String getContent() {
        return content;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }
}
