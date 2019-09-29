package domain.documents;

public interface DocumentRepository {
    void Save(Document document);
    Document Get(DocumentId documentId);
}
