package domain;

import javax.ws.rs.NotFoundException;

public class RepositoryResult<T> {
    private T entity;
    private RepositoryStatus status = RepositoryStatus.ok;

    public RepositoryResult(T entity) {
        this.entity = entity;
    }
    public RepositoryResult(RepositoryStatus status) {
        this.status = status;
    }

    public boolean failed() {
        return status != RepositoryStatus.ok;
    }

    public boolean suceeded() {
        return status == RepositoryStatus.ok;
    }

    public T getEntity() throws ConflictException, NotFoundException {
        switch (status) {
            case allreadyExists : throw new ConflictException();
            case notFound : throw new NotFoundException();
            default : return entity;
        }
    }

    public boolean isNotFound() {
        return RepositoryStatus.notFound.equals(status);
    }

    public boolean isAllreadyExisting() {
        return RepositoryStatus.allreadyExists.equals(status);
    }
}
