package domain;

public class ValidationResult<T> {
    private DomainError error;
    private T entity;

    public ValidationResult(DomainError error) {
        this.error = error;
    }

    public ValidationResult(T entity) {
        this.entity = entity;
    }

    public DomainError getError() {
        return error;
    }

    public T getEntity() {
        return entity;
    }
}
