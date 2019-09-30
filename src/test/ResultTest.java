import domain.*;
import domain.ConflictException;
import domain.RepositoryResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.validation.ValidationException;
import javax.ws.rs.NotFoundException;

class ResultTest {

    @Test
    void createResult() {
        ValidationResult validationResult = new ValidationResult(new DomainError("SomethingBad"));
        Assertions.assertEquals(true, validationResult.failed());
    }

    @Test
    void createResult_exceptionOnError() {
        ValidationResult validationResult = new ValidationResult(new DomainError("SomethingBad"));
        Assertions.assertThrows(ValidationException.class, () -> validationResult.getEntity());
    }

    @Test
    void notFoundResult() {
        RepositoryResult<DummyClass> result = new RepositoryResult<>(RepositoryStatus.notFound);

        Assertions.assertTrue(result.failed());
        Assertions.assertTrue(result.isNotFound());
        Assertions.assertThrows(NotFoundException.class, () -> result.getEntity());
    }

    @Test
    void allreadyExistsResult() {
        RepositoryResult<DummyClass> result = new RepositoryResult<>(RepositoryStatus.allreadyExists);

        Assertions.assertTrue(result.failed());
        Assertions.assertTrue(result.isAllreadyExisting());
        Assertions.assertThrows(ConflictException.class, () -> result.getEntity());
    }

    @Test
    void resultIsFound() {
        RepositoryResult<DummyClass> result = new RepositoryResult<>(new DummyClass());

        Assertions.assertTrue(result.suceeded());
        Assertions.assertFalse(result.failed());
    }
}
