package domain.documents;

import domain.ValidationResult;

public class Document {
    private DocumentId documentId;
    private String content;
    private DocumentType documentType;
    private boolean isDeleted;

    private Document(DocumentId documentId, String content, DocumentType documentType, boolean isDeleted) {
        this.documentId = documentId;
        this.content = content;
        this.documentType = documentType;
        this.isDeleted = isDeleted;
    }

    public static ValidationResult<Document> create(DocumentId documentId, String content, DocumentType documentType) {
        ValidationResult<Document> documentValidationResult = new ValidationResult<>(new Document(documentId, content, documentType, true));
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

    public boolean isDeleted() { return isDeleted; }

    public ValidationResult<Document> updateDocument(String documentContent, DocumentType documentType) {
        if (isDeleted) return new ValidationResult<>(DocumentErrors.CanNotUpdateDeletedDocument());
        return new ValidationResult(Document.ActiveDocument(documentId, documentContent, documentType));
    }

    public ValidationResult<Document> delete() {
        return new ValidationResult<>(Document.DeletedDocument(documentId, content, documentType));
    }

    private static Document DeletedDocument(DocumentId documentId, String content, DocumentType documentType) {
        return new Document(documentId, content, documentType, true);
    }

    private static Document ActiveDocument(DocumentId documentId, String content, DocumentType documentType) {
        return new Document(documentId, content, documentType, false);
    }
}
