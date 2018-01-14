package entityvalidator.process;

import java.lang.reflect.Field;
import java.util.List;

import entityvalidator.ValidationError;
import entityvalidator.exception.UnsupportedFieldException;
import entityvalidator.type.notation.Regex;

public class RegexValidator extends BaseFieldValidator implements FieldValidator {

    public RegexValidator(Field field, Object entity) throws IllegalAccessException {
        super(field, entity);
    }
    @Override
    public void validate(List<ValidationError> errors) throws UnsupportedFieldException, IllegalAccessException {
        if (null != value) {
            String valString = value.toString();
            Regex regex = field.getAnnotation(Regex.class);
            boolean isValid = false;
            if (valString.matches(regex.regex())) {
                isValid = true;
            }
            if (!isValid) {
                ValidationError validationError = new ValidationError(fieldName);
                String errorDesc = regex.errorDesc() + " : " + regex.regex();
                validationError.setErrorDescription(errorDesc);
                validationError.setActualValue(valString);
                errors.add(validationError);
            }
        }
    }
}
