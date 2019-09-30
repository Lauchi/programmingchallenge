package domain;


import javax.validation.ValidationException;

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

    public boolean failed() {
        return error != null;
    }

    public boolean suceeded() {
        return error == null;
    }

    public T getEntity() throws ValidationException {
        if (failed()) throw new ValidationException(error.getErrorKey());
        return entity;
    }
}
