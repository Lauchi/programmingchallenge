package domain.documents;

public class DocumentBuilder {

    private boolean deleted;
    private String content;
    private DocumentType documentType;
    private DocumentId documentId;

    public DocumentBuilder(Document document) {
        deleted = document.isDeleted();
        content = document.getContent();
        documentType = document.getDocumentType();
        documentId = document.getDocumentId();
    }

    public DocumentBuilder WithContent(String documentContent) {
        content = documentContent;
        return this;
    }

    public DocumentBuilder WithDocumentType(DocumentType documentType) {
        this.documentType = documentType;
        return this;
    }

    public Document Build() {
        return new Document(documentId, content, documentType, deleted);
    }

    public DocumentBuilder WithDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }
}
