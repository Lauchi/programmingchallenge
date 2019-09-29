package adapters.persistence.memory;

import domain.RepositoryResult;
import domain.RepositoryStatus;
import domain.documents.Document;
import domain.documents.DocumentId;
import domain.documents.DocumentRepository;

import java.util.ArrayList;
import java.util.List;

public class DocumentRepositoryImpl implements DocumentRepository {
    private List<Document> documents = new ArrayList<>();

    @Override
    public RepositoryResult<Document> Save(Document document) {
        RepositoryResult<Document> get = Get(document.getDocumentId());
        if (!get.isNotFound()) {
            return new RepositoryResult<>(RepositoryStatus.allreadyExists);
        }

        documents.add(document);
        return new RepositoryResult<>(document);
    }

    @Override
    public RepositoryResult<Document> Get(DocumentId documentId) {
        for (Document document : documents) {
            if (document.getDocumentId().equals(documentId)) {
                return new RepositoryResult<>(document);
            }
        }

        return new RepositoryResult<>(RepositoryStatus.notFound);
    }

    @Override
    public RepositoryResult<Document> Delete(DocumentId documentId) {
        RepositoryResult<Document> result = Get(documentId);
        Document document = result.getEntity();
        documents.remove(document);
        return new RepositoryResult<>(document);
    }
}
