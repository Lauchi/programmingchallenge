package domain.documents;

import domain.ValidationResult;

public class DocumentType {
    private String documentType;

    private DocumentType(String documentType) {
        this.documentType = documentType;
    }

    public static ValidationResult<DocumentType> Create(String type) {
        if (type == null || type.isEmpty()) return new ValidationResult(DocumentErrors.DocumentTypeCanNotBeNullOrEmpty());
        return new ValidationResult(new DocumentType(type));
    }

    public String getDocumentType() {
        return documentType;
    }
}
