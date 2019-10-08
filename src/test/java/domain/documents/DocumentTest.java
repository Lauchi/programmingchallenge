package domain.documents;

import domain.ValidationResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DocumentTest {

    @Test
    void testCreateDocument() {
        DocumentType pdf = DocumentHelpers.ValidDocumentType();
        DocumentId id = DocumentHelpers.ValidDocumentId();
        String content = "Content von body";
        ValidationResult<Document> document = Document.create(id, content, pdf);

        Assertions.assertTrue(document.suceeded());
        Assertions.assertEquals(content, document.getEntity().getContent());
    }

    @Test
    void DocumentTypeEquals() {
        String documentType = "pdf";

        DocumentType documentType1 = DocumentType.create(documentType).getEntity();
        DocumentType documentType2 = DocumentType.create(documentType).getEntity();

        Assertions.assertTrue(documentType1.equals(documentType2));
    }

    @Test
    void testCreateDocumentEmpty() {
        ValidationResult<DocumentType> pdf = DocumentType.create("");
        Assertions.assertEquals(pdf.getError().getErrorKey(), "DocumentTypeCanNotBeNullOrEmpty");
    }

    @Test
    void testDeletDocument() {
        Document document = DocumentHelpers.ValidDocument();
        ValidationResult<Document> result = document.delete();
        Assertions.assertTrue(result.getEntity().isDeleted());
    }

    @Test
    void testUpdateDocument() {
        Document documentOriginal = DocumentHelpers.ValidDocument();
        String newContent = "new Content";
        DocumentType newDocumentType = DocumentHelpers.AnotherValidDocumentType();
        ValidationResult<Document> result = documentOriginal.updateDocument(newContent, newDocumentType);

        Document updatedDocument = result.getEntity();
        Assertions.assertNotEquals(updatedDocument.getContent(), documentOriginal.getContent());
        Assertions.assertNotEquals(updatedDocument.getDocumentType(), documentOriginal.getDocumentType());
        Assertions.assertEquals(updatedDocument.getContent(), newContent);
        Assertions.assertEquals(updatedDocument.getDocumentType(), newDocumentType);
    }
}