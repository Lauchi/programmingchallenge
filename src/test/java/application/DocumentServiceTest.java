package application;

import adapters.persistence.memory.DocumentRepositoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocumentServiceTest {

    @Test
    void createDocument() {
        DocumentService documentService = new DocumentService(new DocumentRepositoryImpl());

    }

    @Test
    void updateDocument() {
    }
}