package entityvalidator.process;

import entityvalidator.ValidationError;
import entityvalidator.exception.UnsupportedFieldException;
import entityvalidator.type.notation.NumberFormat;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.List;

public class NumberFormatValidator extends BaseFieldValidator implements FieldValidator {

    public NumberFormatValidator (Field field, Object entity) throws IllegalAccessException {
        super(field, entity);
    }

    @Override
    public void validate(List<ValidationError> errors) throws UnsupportedFieldException {
        if (null != value) {
            if (value instanceof Number) {
                String valueString = value.toString();
                NumberFormat numberFormat = field.getAnnotation(NumberFormat.class);
                DecimalFormat decimalFormat = new DecimalFormat(numberFormat.numberFormat());
                int maxFractionDigits = decimalFormat.getMaximumFractionDigits();
                int actualFractionDigits = valueString.substring(valueString.indexOf(".") + 1, valueString.length()).length();
                boolean isNumberFormatValid = numberFormat.lengthCriteria().isValid(maxFractionDigits, actualFractionDigits);
                if (!isNumberFormatValid) {
                    ValidationError validationError = new ValidationError(fieldName);
                    String errorDesc = numberFormat.errorDesc() + numberFormat.numberFormat() + ":" + value;
                    validationError.setActualValue(value.toString());
                    validationError.setErrorDescription(errorDesc);
                    errors.add(validationError);
                }
            }
        }
    }
}
