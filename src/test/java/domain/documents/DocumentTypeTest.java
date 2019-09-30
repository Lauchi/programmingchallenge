package domain.documents;

import domain.ValidationResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DocumentTypeTest {

    @Test
    void DocumentTypeEquals() {
        String documentType = "pdf";

        DocumentType documentType1 = DocumentType.create(documentType).getEntity();
        DocumentType documentType2 = DocumentType.create(documentType).getEntity();

        Assertions.assertTrue(documentType1.equals(documentType2));
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
}