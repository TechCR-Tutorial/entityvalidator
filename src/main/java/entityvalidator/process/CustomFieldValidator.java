package entityvalidator.process;

import java.lang.reflect.Field;
import java.util.List;

import entityvalidator.ValidationError;
import entityvalidator.exception.UnsupportedFieldException;
import entityvalidator.type.notation.CustomValidate;

public abstract class CustomFieldValidator<T> extends BaseFieldValidator implements FieldValidator {

    public CustomFieldValidator(Field field, Object entity) throws IllegalAccessException {
        super(field, entity);
    }

    @Override
    public void validate(List<ValidationError> errors) throws UnsupportedFieldException, IllegalAccessException {
        CustomValidate customValidate = field.getAnnotation(CustomValidate.class);
        validate((T) value, customValidate, errors);
    }

    protected abstract void validate(T value, CustomValidate customValidate, List<ValidationError> errors);

}
