package application.documents;

public class DeleteDocumentCommand {
    private String documentId;

    public DeleteDocumentCommand(String documentIdRaw) {

        documentId = documentIdRaw;
    }

    public String getDocumentId() {
        return documentId;
    }
}
