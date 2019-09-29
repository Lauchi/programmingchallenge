package adapters.persistence.memory;

import domain.RepositoryResult;
import domain.documents.Document;
import domain.documents.DocumentHelpers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DocumentRepositoryImplTest {

    @Test
    void saveAndGet() {
        DocumentRepositoryImpl documentRepository = new DocumentRepositoryImpl();
        Document document = DocumentHelpers.ValidDocument();
        documentRepository.insert(document);
        Document result = documentRepository.get(document.getDocumentId()).getEntity();

        Assertions.assertEquals(document.getDocumentId(), result.getDocumentId());
        Assertions.assertEquals(document.getContent(), result.getContent());
        Assertions.assertEquals(document.getDocumentType(), result.getDocumentType());
    }

    @Test
    void saveAndGet_NotFoundForDeletedDocument() {
        DocumentRepositoryImpl documentRepository = new DocumentRepositoryImpl();
        Document document = DocumentHelpers.DeletedDocument();
        documentRepository.insert(document);
        RepositoryResult<Document> result = documentRepository.get(document.getDocumentId());

        Assertions.assertTrue(result.isNotFound());
    }

    @Test
    void saveAndGet_InsertTwice() {
        DocumentRepositoryImpl documentRepository = new DocumentRepositoryImpl();
        Document document = DocumentHelpers.ValidDocument();

        documentRepository.insert(document);
        RepositoryResult<Document> result = documentRepository.insert(document);

        Assertions.assertTrue(result.isAllreadyExisting());
    }

    @Test
    void update_NotFound() {
        DocumentRepositoryImpl documentRepository = new DocumentRepositoryImpl();
        Document document = DocumentHelpers.ValidDocument();

        RepositoryResult<Document> result = documentRepository.update(document);

        Assertions.assertTrue(result.isNotFound());
    }

    @Test
    void update_happyPath() {
        DocumentRepositoryImpl documentRepository = new DocumentRepositoryImpl();
        Document documentOld = DocumentHelpers.ValidDocument();
        String newContent = "New Content";
        Document documentNew = DocumentHelpers.ValidDocumentWithContent(newContent);

        documentRepository.insert(documentOld);
        documentRepository.update(documentNew);

        Document result = documentRepository.get(documentNew.getDocumentId()).getEntity();

        Assertions.assertEquals(result.getContent(), newContent);
    }
}