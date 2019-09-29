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
    public RepositoryResult<Document> insert(Document document) {
        RepositoryResult<Document> result = get(document.getDocumentId());
        if (!result.isNotFound()) {
            return new RepositoryResult<>(RepositoryStatus.allreadyExists);
        }

        documents.add(document);
        return new RepositoryResult<>(document);
    }

    @Override
    public RepositoryResult<Document> get(DocumentId documentId) {
        for (Document document : documents) {
            if (document.getDocumentId().equals(documentId) && !document.isDeleted()) {
                return new RepositoryResult<>(document);
            }
        }

        return new RepositoryResult<>(RepositoryStatus.notFound);
    }

    @Override
    public RepositoryResult<Document> update(Document document) {
        RepositoryResult<Document> result = get(document.getDocumentId());
        if (result.isNotFound()) return result;

        documents.remove(result.getEntity());
        documents.add(document);

        return new RepositoryResult<>(document);
    }
}
