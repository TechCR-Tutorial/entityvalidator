package entityvalidator.process;

import java.lang.reflect.Field;
import java.util.List;

import entityvalidator.ValidationError;
import entityvalidator.exception.UnsupportedFieldException;
import entityvalidator.type.notation.Length;

public class FieldLengthValidator extends BaseFieldValidator implements FieldValidator {

    public FieldLengthValidator(Field field, Object entity) throws IllegalAccessException {
        super(field, entity);
    }

    @Override
    public void validate(List<ValidationError> errors) throws UnsupportedFieldException {
        if (null != value) {
            if (value instanceof Double || value instanceof Float) {
                throw new UnsupportedFieldException("Length Not Support Type");
            }
            String valueStr = value.toString();
            Length length = field.getAnnotation(Length.class);
            boolean isValid = length.lengthCriteriaType().isValid(length.length(), valueStr.length());
            if (!isValid) {
                ValidationError validationError = new ValidationError(fieldName);
                String errorDesc = length.errorDesc() + " " + length.lengthCriteriaType().name() + ":" +
                         length.length();
                validationError.setErrorDescription(errorDesc);
                validationError.setActualValue(valueStr);
                errors.add(validationError);
            }
        }
    }
}
