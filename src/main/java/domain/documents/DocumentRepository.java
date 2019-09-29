package domain.documents;

import domain.RepositoryResult;
import domain.documents.Document;
import domain.documents.DocumentId;

public interface DocumentRepository {
    RepositoryResult<Document> Save(Document document);
    RepositoryResult<Document> Get(DocumentId documentId);
}
