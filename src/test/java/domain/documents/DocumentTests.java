package domain.documents;

import domain.ValidationResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class DocumentTests {

    @Test
    public void testCreateDocument() {
        DocumentType pdf = DocumentType.Create("pdf").getEntity();
        DocumentId id = DocumentId.Create("12345678901234567890").getEntity();
        Document.Create(id, "Content von body", pdf);
    }

    @Test
    public void testCreateDocumentId() {
        String documentIdRaw = "12345678901234567890";
        DocumentId id = DocumentId.Create(documentIdRaw).getEntity();
        Assertions.assertEquals(documentIdRaw, id.getDocumentId());
    }

    @Test
    public void testCreateDocumentId_tooShort() {
        String documentIdRaw = "1234567890123456789";
        ValidationResult<DocumentId> result = DocumentId.Create(documentIdRaw);
        Assertions.assertEquals(DocumentErrors.DocumentIdHasToBeA20CharacterAlphanumericString().getErrorKey(), result.getError().getErrorKey());
    }

    @Test
    public void testCreateDocumentId_tooBig() {
        String documentIdRaw = "123456789012345678901";
        ValidationResult<DocumentId> result = DocumentId.Create(documentIdRaw);
        Assertions.assertEquals(DocumentErrors.DocumentIdHasToBeA20CharacterAlphanumericString().getErrorKey(), result.getError().getErrorKey());
    }

    @Test
    public void testCreateDocumentId_InvalidCharactes() {
        String documentIdRaw = "12345678901/34567-90";
        ValidationResult<DocumentId> result = DocumentId.Create(documentIdRaw);
        Assertions.assertEquals(DocumentErrors.DocumentIdHasToBeA20CharacterAlphanumericString().getErrorKey(), result.getError().getErrorKey());
    }

    @Test
    public void testCreateDocumentNullType() {
        ValidationResult<DocumentType> pdf = DocumentType.Create(null);
        Assertions.assertEquals(pdf.getError().getErrorKey(), "DocumentTypeCanNotBeNullOrEmpty");
    }

    @Test
    public void testCreateDocumentEmpty() {
        ValidationResult<DocumentType> pdf = DocumentType.Create("");
        Assertions.assertEquals(pdf.getError().getErrorKey(), "DocumentTypeCanNotBeNullOrEmpty");
    }
}