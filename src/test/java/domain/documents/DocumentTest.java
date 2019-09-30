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
    void testCreateDocumentId() {
        String documentIdRaw = "12345678901234567890";
        DocumentId id = DocumentId.create(documentIdRaw).getEntity();
        Assertions.assertEquals(documentIdRaw, id.getDocumentId());
    }

    @Test
    void testCreateDocumentId_tooShort() {
        String documentIdRaw = "1234567890123456789";
        ValidationResult<DocumentId> result = DocumentId.create(documentIdRaw);
        Assertions.assertEquals(DocumentErrors.DocumentIdHasToBeA20CharacterAlphanumericString().getErrorKey(), result.getError().getErrorKey());
    }

    @Test
    void testCreateDocumentId_tooBig() {
        String documentIdRaw = "123456789012345678901";
        ValidationResult<DocumentId> result = DocumentId.create(documentIdRaw);
        Assertions.assertEquals(DocumentErrors.DocumentIdHasToBeA20CharacterAlphanumericString().getErrorKey(), result.getError().getErrorKey());
    }

    @Test
    void DocumentIdEquals() {
        String documentIdRaw = "12345678901234567890";

        DocumentId id1 = DocumentId.create(documentIdRaw).getEntity();
        DocumentId id2 = DocumentId.create(documentIdRaw).getEntity();

        Assertions.assertTrue(id1.equals(id2));
    }

    @Test
    void DocumentTypeEquals() {
        String documentType = "pdf";

        DocumentType documentType1 = DocumentType.create(documentType).getEntity();
        DocumentType documentType2 = DocumentType.create(documentType).getEntity();

        Assertions.assertTrue(documentType1.equals(documentType2));
    }

    @Test
    void testCreateDocumentId_InvalidCharactes() {
        String documentIdRaw = "12345678901/34567-90";
        ValidationResult<DocumentId> result = DocumentId.create(documentIdRaw);
        Assertions.assertEquals(DocumentErrors.DocumentIdHasToBeA20CharacterAlphanumericString().getErrorKey(), result.getError().getErrorKey());
    }

    @Test
    void testCreateDocumentNullType() {
        ValidationResult<DocumentType> pdf = DocumentType.create(null);
        Assertions.assertEquals(pdf.getError().getErrorKey(), "DocumentTypeCanNotBeNullOrEmpty");
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
}