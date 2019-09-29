package domain.documents;

public interface DocumentRepository {
    void Save(Document document);
    void Get(DocumentId documentId);
}
