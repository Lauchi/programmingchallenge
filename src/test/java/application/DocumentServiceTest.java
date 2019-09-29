package application;

import adapters.persistence.memory.DocumentRepositoryImpl;
import application.documents.DocumentService;
import org.junit.jupiter.api.Test;

class DocumentServiceTest {

    @Test
    void createDocument() {
        DocumentService documentService = new DocumentService(new DocumentRepositoryImpl());

    }

    @Test
    void updateDocument() {
    }
}