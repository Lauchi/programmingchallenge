package domain.documents;

import domain.ValidationResult;

public class DocumentHelpers {

    public static DocumentType ValidDocumentType() {
        return DocumentType.create("pdf").getEntity();
    }

    public static DocumentId ValidDocumentId() {
        return DocumentId.create("12345678901234567890").getEntity();
    }

    public static Document ValidDocument() {
        return Document.create(ValidDocumentId(), "Random Content", ValidDocumentType()).getEntity();
    }

    public static Document DeletedDocument() {
        Document document = ValidDocument();
        ValidationResult<Document> deleteResult = document.delete();
        return deleteResult.getEntity();
    }
}
