package domain.documents;

import domain.ValidationResult;

public class Document {
    private DocumentId documentId;
    private String content;
    private DocumentType documentType;

    private Document(DocumentId documentId, String content, DocumentType documentType) {
        this.documentId = documentId;
        this.content = content;
        this.documentType = documentType;
    }

    public static ValidationResult<Document> Create(DocumentId documentId, String content, DocumentType documentType) {
        ValidationResult<Document> documentValidationResult = new ValidationResult<>(new Document(documentId, content, documentType));
        return documentValidationResult;
    }

    public DocumentId getDocumentId() {
        return documentId;
    }

    public String getContent() {
        return content;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }
}
