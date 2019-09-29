package application;

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
}
