package application;

import domain.documents.Document;
import domain.documents.DocumentRepository;
import domain.documents.DocumentType;

import java.util.UUID;

public class DocumentService {
    private DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public UUID CreateDocument(CreateDocumentCommand command) {
        DocumentType documentType = DocumentType.Create(command.getDocumentType()).getEntity();
        Document document = Document.Create(command.getDocumentId(), command.getContent(), documentType).getEntity();
        documentRepository.Save(document);
        return document.getDocumentId();
    }
}
