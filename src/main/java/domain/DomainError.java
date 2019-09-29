package domain;

public class DomainError {
    private String errorKey;

    public DomainError(String errorKey) {
        this.errorKey = errorKey;
    }

    public String getErrorKey() {
        return errorKey;
    }
}
