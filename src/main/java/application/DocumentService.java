package application;

import domain.ValidationResult;
import domain.documents.Document;
import domain.documents.DocumentId;
import domain.documents.DocumentRepository;
import domain.documents.DocumentType;

public class DocumentService {
    private DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public DocumentId CreateDocument(CreateDocumentCommand command) {
        DocumentType documentType = DocumentType.Create(command.getDocumentType()).getEntity();
        DocumentId documentId = DocumentId.Create(command.getDocumentId()).getEntity();
        Document document = Document.Create(documentId, command.getContent(), documentType).getEntity();
        documentRepository.Save(document);
        return document.getDocumentId();
    }

    public void UpdateDocument(UpdateDocumentCommand command) {
        DocumentId documentId = DocumentId.Create(command.getDocumentId()).getEntity();
        Document document = documentRepository.Get(documentId);
        DocumentType documentType = DocumentType.Create(command.getDocumentType()).getEntity();
        ValidationResult<Document> documentValidationResult = document.updateDocument(command.getDocumentContent(), documentType);
        Document documentUpdated = documentValidationResult.getEntity();
        documentRepository.Save(documentUpdated);
    }

    public Document GetDocument(GetDocumentCommand command) {
        DocumentId documentId = DocumentId.Create(command.getDocumentId()).getEntity();
        return documentRepository.Get(documentId);
    }
}
