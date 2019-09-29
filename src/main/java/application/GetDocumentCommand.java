package application;

public class GetDocumentCommand {
    private String documentId;

    public GetDocumentCommand(String documentId) {

        this.documentId = documentId;
    }

    public String getDocumentId() {
        return documentId;
    }
}
