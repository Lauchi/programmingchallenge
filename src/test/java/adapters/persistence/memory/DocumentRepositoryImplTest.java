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
        documentRepository.save(document);
        Document result = documentRepository.get(document.getDocumentId()).getEntity();

        Assertions.assertEquals(document.getDocumentId(), result.getDocumentId());
        Assertions.assertEquals(document.getContent(), result.getContent());
        Assertions.assertEquals(document.getDocumentType(), result.getDocumentType());
    }

    @Test
    void saveAndGet_NotFoundForDeletedDocument() {
        DocumentRepositoryImpl documentRepository = new DocumentRepositoryImpl();
        Document document = DocumentHelpers.DeletedDocument();
        documentRepository.save(document);
        RepositoryResult<Document> result = documentRepository.get(document.getDocumentId());

        Assertions.assertTrue(result.isNotFound());
    }
}