package entityvalidator.process;

import java.lang.reflect.Field;
import java.util.List;

import entityvalidator.ValidationError;
import entityvalidator.exception.UnsupportedFieldException;

public interface FieldValidator {
    void validate(List<ValidationError> errors) throws UnsupportedFieldException, IllegalAccessException;
}
