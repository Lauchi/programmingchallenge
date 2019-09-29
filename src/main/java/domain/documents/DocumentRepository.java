package domain.documents;

import domain.RepositoryResult;
import domain.documents.Document;
import domain.documents.DocumentId;

public interface DocumentRepository {
    RepositoryResult<Document> insert(Document document);
    RepositoryResult<Document> get(DocumentId documentId);
    RepositoryResult<Document> update(Document document);
}
