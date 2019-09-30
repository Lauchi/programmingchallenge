package domain.documents;

import domain.RepositoryResult;

public interface DocumentRepository {
    RepositoryResult<Document> insert(Document document);
    RepositoryResult<Document> get(DocumentId documentId);
    RepositoryResult<Document> update(Document document);
}
