package entityvalidator.process;

import java.lang.reflect.Field;
import java.util.List;

import entityvalidator.ValidationError;
import entityvalidator.exception.UnsupportedFieldException;
import entityvalidator.type.LengthCriteriaType;
import entityvalidator.type.notation.LengthBetween;

public class FieldLengthBetweenValidator extends BaseFieldValidator implements FieldValidator {

    public FieldLengthBetweenValidator(Field field, Object entity) throws IllegalAccessException {
        super(field, entity);
    }

    @Override
    public void validate(List<ValidationError> errors) throws UnsupportedFieldException {
        if (null != value) {
            if (value instanceof Float || value instanceof Double) {
                throw new UnsupportedFieldException("Unsupported type");
            }
            String valueString = value.toString();
            LengthBetween lengthBetween = field.getAnnotation(LengthBetween.class);
            boolean minLengthValid = lengthBetween.minLengthCriteria().isValid(lengthBetween.minLength(),
                    valueString.length());
            boolean maxLenghtValid = lengthBetween.maxLengthCriteria().isValid(lengthBetween.maxLength(),
                    valueString.length());
            if (!minLengthValid || !maxLenghtValid) {
                ValidationError validationError = new ValidationError(fieldName);
                String errorDesc = lengthBetween.errorDesc() + " " + lengthBetween.minLengthCriteria().name() + " : " +
                        lengthBetween.minLength() + " & " + lengthBetween.maxLengthCriteria().name() + " : " +
                        lengthBetween.maxLength();
                validationError.setErrorDescription(errorDesc);
                validationError.setActualValue(valueString);
                errors.add(validationError);
            }
        }
    }
}
