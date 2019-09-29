package domain.documents;

import domain.DomainError;

public class DocumentErrors {
    public static DomainError DocumentTypeCanNotBeNullOrEmpty() {
        return new DomainError("DocumentTypeCanNotBeNullOrEmpty");
    }

    public static DomainError DocumentIdHasToBeA20CharacterAlphanumericString() {
        return new DomainError("DocumentIdHasToBeA20CharacterAlphanumericString");
    }
}
