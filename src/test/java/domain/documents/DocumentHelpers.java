package domain.documents;

public class DocumentHelpers {

    public static DocumentType ValidDocumentType() {
        return DocumentType.Create("pdf").getEntity();
    }

    public static DocumentId ValidDocumentId() {
        return DocumentId.Create("12345678901234567890").getEntity();
    }

    public static Document ValidDocument() {
        return Document.Create(ValidDocumentId(), "Random Content", ValidDocumentType()).getEntity();
    }
}
