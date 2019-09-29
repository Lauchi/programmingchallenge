package application;

import adapters.persistence.memory.DocumentRepositoryImpl;
import application.documents.CreateDocumentCommand;
import application.documents.DocumentService;
import domain.ConflictException;
import domain.documents.Document;
import domain.documents.DocumentHelpers;
import domain.documents.DocumentId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DocumentServiceTest {

    @Test
    void createDocument() {
        DocumentService documentService = new DocumentService(new DocumentRepositoryImpl());

        DocumentId documentIdInput = DocumentHelpers.ValidDocumentId();
        CreateDocumentCommand command = new CreateDocumentCommand(
                documentIdInput.getDocumentId(),
                "content",
                DocumentHelpers.ValidDocumentType().getDocumentType());
        Document documentCreated = documentService.CreateDocument(command);

        Assertions.assertEquals(documentCreated.getDocumentId(), documentIdInput);
    }

    @Test
    void createDocumentTwice() {
        DocumentService documentService = new DocumentService(new DocumentRepositoryImpl());

        DocumentId documentIdInput = DocumentHelpers.ValidDocumentId();
        CreateDocumentCommand command = new CreateDocumentCommand(
                documentIdInput.getDocumentId(),
                "content",
                DocumentHelpers.ValidDocumentType().getDocumentType());

        documentService.CreateDocument(command);

        Assertions.assertThrows(ConflictException.class, () -> documentService.CreateDocument(command));
    }

    @Test
    void updateDocument() {

    }
}