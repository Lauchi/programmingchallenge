package domain.documents;

import domain.ValidationResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocumentId {
    private String documentId;

    private DocumentId(String documentId) {
        this.documentId = documentId;
    }

    public static ValidationResult<DocumentId> Create(String id) {
        String regex = "^[a-zA-Z0-9]{20}+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(id);
        if (!matcher.matches()) {
            return new ValidationResult<>(DocumentErrors.DocumentIdHasToBeA20CharacterAlphanumericString());
        }

        return new ValidationResult<>(new DocumentId(id));
    }

    public String getDocumentId() {
        return documentId;
    }
}
