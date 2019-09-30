package application;

import adapters.persistence.memory.DocumentRepositoryImpl;
import application.documents.CreateDocumentCommand;
import application.documents.DocumentService;
import application.documents.UpdateDocumentCommand;
import domain.ConflictException;
import domain.documents.Document;
import domain.documents.DocumentHelpers;
import domain.documents.DocumentId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.ws.rs.NotFoundException;

class DocumentServiceTest {

    @BeforeEach
    public void setUp() {
        documentService = new DocumentService(new DocumentRepositoryImpl());
    }

    private DocumentService documentService;

    @Test
    void createDocument() {
        DocumentId documentIdInput = DocumentHelpers.ValidDocumentId();
        CreateDocumentCommand command = getCreateDocumentCommand(documentIdInput);

        Document documentCreated = documentService.CreateDocument(command);

        Assertions.assertEquals(documentCreated.getDocumentId(), documentIdInput);
    }

    @Test
    void createDocumentTwice() {
        DocumentId documentIdInput = DocumentHelpers.ValidDocumentId();
        CreateDocumentCommand command = getCreateDocumentCommand(documentIdInput);

        documentService.CreateDocument(command);

        Assertions.assertThrows(ConflictException.class, () -> documentService.CreateDocument(command));
    }

    @Test
    void updateDocument_NotFound() {
        DocumentId documentIdInput = DocumentHelpers.ValidDocumentId();
        UpdateDocumentCommand command = new UpdateDocumentCommand(
                documentIdInput.getDocumentId(),
                "newContent",
                DocumentHelpers.ValidDocumentType().getDocumentType());

        Assertions.assertThrows(NotFoundException.class, () -> {
            documentService.UpdateDocument(command);
        });
    }

    private CreateDocumentCommand getCreateDocumentCommand(DocumentId documentIdInput) {
        return new CreateDocumentCommand(
                documentIdInput.getDocumentId(),
                "content",
                DocumentHelpers.ValidDocumentType().getDocumentType());
    }
}