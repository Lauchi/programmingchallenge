package domain.documents;

import domain.ValidationResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class DocumentTests {

    @Test
    public void testCreateDocument() {
        DocumentType pdf = DocumentType.Create("pdf").getEntity();
        Document.Create(new DocumentId(UUID.randomUUID()), "Content von body", pdf);
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