import domain.DomainError;
import domain.ValidationResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.validation.ValidationException;

public class ValidationResultTests {

    @Test
    public void createResult() {
        ValidationResult validationResult = new ValidationResult(new DomainError("SomethingBad"));
        Assertions.assertEquals(true, validationResult.failed());
    }

    @Test
    public void createResult_exceptionOnError() {
        ValidationResult validationResult = new ValidationResult(new DomainError("SomethingBad"));
        Assertions.assertThrows(ValidationException.class, () -> validationResult.getEntity());
    }
}
