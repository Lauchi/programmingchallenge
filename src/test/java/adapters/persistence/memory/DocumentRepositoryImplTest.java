package adapters.persistence.memory;

import domain.documents.Document;
import domain.documents.DocumentHelpers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocumentRepositoryImplTest {

    @Test
    void saveAndGet() {
        DocumentRepositoryImpl documentRepository = new DocumentRepositoryImpl();
        Document document = DocumentHelpers.ValidDocument();
        documentRepository.Save(document);
        Document get = documentRepository.Get(document.getDocumentId());

        Assertions.assertEquals(document.getDocumentId(), get.getDocumentId());
        Assertions.assertEquals(document.getContent(), get.getContent());
        Assertions.assertEquals(document.getDocumentType(), get.getDocumentType());
    }
}