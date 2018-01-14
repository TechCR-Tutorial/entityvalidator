package entityvalidator.process;

import java.lang.reflect.Field;
import java.util.List;

import entityvalidator.ValidationError;
import entityvalidator.type.notation.Mandatory;

public class FieldMandatoryValidator extends BaseFieldValidator implements FieldValidator {

    public FieldMandatoryValidator(Field field, Object entity) throws IllegalAccessException {
        super(field, entity);
    }

    @Override
    public void validate(List<ValidationError> errors) {
        Mandatory mandatory = field.getAnnotation(Mandatory.class);
        if (null == value) {
            String errorDesc = mandatory.errorDesc();
            ValidationError validationError = new ValidationError(fieldName);
            validationError.setErrorDescription(errorDesc);
            errors.add(validationError);
        }
    }
}
