package application.documents;

public class GetDocumentQuerry {
    private String documentId;

    public GetDocumentQuerry(String documentId) {

        this.documentId = documentId;
    }

    public String getDocumentId() {
        return documentId;
    }
}
