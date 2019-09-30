package application.documents;

import adapters.persistence.memory.DocumentRepositoryImpl;
import domain.RepositoryResult;
import domain.ValidationResult;
import domain.documents.Document;
import domain.documents.DocumentId;
import domain.documents.DocumentRepository;
import domain.documents.DocumentType;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

public class DocumentService {

    private DocumentRepository documentRepository;

    @Inject
    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    private static DocumentService instance;
    public static synchronized DocumentService getInstance () {
        if (DocumentService.instance == null) {
            DocumentService.instance = new DocumentService(new DocumentRepositoryImpl());
        }
        return DocumentService.instance;
    }

    public Document CreateDocument(CreateDocumentCommand command) {
        DocumentType documentType = DocumentType.create(command.getDocumentType()).getEntity();
        DocumentId documentId = DocumentId.create(command.getDocumentId()).getEntity();
        Document document = Document.create(documentId, command.getContent(), documentType).getEntity();

        RepositoryResult<Document> result = documentRepository.insert(document);
        return result.getEntity();
    }

    public void UpdateDocument(UpdateDocumentCommand command) {
        DocumentId documentId = parseDocumentIdAndThrowIfInvalid(command.getDocumentId());
        DocumentType documentType = DocumentType.create(command.getDocumentType()).getEntity();
        Document document = documentRepository.get(documentId).getEntity();

        ValidationResult<Document> documentValidationResult = document.updateDocument(command.getDocumentContent(), documentType);

        Document documentUpdated = documentValidationResult.getEntity();
        documentRepository.update(documentUpdated);
    }

    public Document GetDocument(GetDocumentQuerry command) {
        DocumentId documentId = parseDocumentIdAndThrowIfInvalid(command.getDocumentId());

        return documentRepository.get(documentId).getEntity();
    }

    public Document DeleteDocument(DeleteDocumentCommand command) {
        DocumentId documentId = parseDocumentIdAndThrowIfInvalid(command.getDocumentId());
        Document document = documentRepository.get(documentId).getEntity();

        ValidationResult<Document> deleteResult = document.delete();

        RepositoryResult<Document> result = documentRepository.update(deleteResult.getEntity());
        return result.getEntity();
    }

    private DocumentId parseDocumentIdAndThrowIfInvalid(String documentId) {
        ValidationResult<DocumentId> documentIdResult = DocumentId.create(documentId);
        if (documentIdResult.failed()) throw new NotFoundException(documentId);
        return documentIdResult.getEntity();
    }
}
