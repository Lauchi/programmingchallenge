package domain.documents;

import domain.ValidationResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DocumentIdTest {

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
    void testCreateDocumentId_InvalidCharactes() {
        String documentIdRaw = "12345678901/34567-90";
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
}