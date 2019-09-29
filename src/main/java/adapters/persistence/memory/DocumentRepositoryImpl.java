package adapters.persistence.memory;

import domain.documents.Document;
import domain.documents.DocumentId;
import domain.documents.DocumentRepository;

import java.util.ArrayList;
import java.util.List;

public class DocumentRepositoryImpl implements DocumentRepository {
    private List<Document> documents = new ArrayList<>();

    @Override
    public void Save(Document document) {
        documents.add(document);
    }

    @Override
    public Document Get(DocumentId documentId) {
        for (Document document : documents) {
            if (document.getDocumentId().equals(documentId)) {
                return document;
            }
        }

        return null;
    }
}
