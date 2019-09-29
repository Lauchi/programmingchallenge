package domain.documents;

import java.util.UUID;

public class DocumentId {
    private UUID documentId;

    public DocumentId(UUID documentId) {
        this.documentId = documentId;
    }

    public UUID getDocumentId() {
        return documentId;
    }
}
