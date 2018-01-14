package entityvalidator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entityvalidator.exception.UnsupportedFieldException;
import entityvalidator.process.EntityFieldValidator;
import entityvalidator.process.FieldValidator;
import entityvalidator.process.FieldValidatorResolver;
import entityvalidator.type.notation.ExcludeParent;


public class Validator<T> {

    private List<ValidationError> validationErrors;

    public void validate(T bean) throws IllegalAccessException, UnsupportedFieldException {
        validate(bean, null);
    }

    public void validate(T bean, List<ValidationError> errors) throws IllegalAccessException, UnsupportedFieldException {
        this.validationErrors =  null == errors ? new ArrayList<>() : errors;

        EntityFieldValidator entityFieldValidator = new EntityFieldValidator(bean);
        entityFieldValidator.validate(validationErrors);

    }

    public List<ValidationError> getValidationErrors() {
        return validationErrors;
    }
}
