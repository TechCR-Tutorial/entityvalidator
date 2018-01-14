package entityvalidator.process;

import entityvalidator.ValidationError;
import entityvalidator.exception.UnsupportedFieldException;
import entityvalidator.type.notation.ConditionRule;
import entityvalidator.type.notation.ConditionValidation;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
import org.apache.commons.jexl2.ObjectContext;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class ConditionValidator extends BaseFieldValidator implements FieldValidator {

    public ConditionValidator(Field field, Object entity) throws IllegalAccessException {
        super(field, entity);
    }

    public ConditionValidator(Object entity) throws IllegalAccessException {
        super(null, entity);
    }

    @Override
    public void validate(List<ValidationError> errors) throws UnsupportedFieldException, IllegalAccessException {

        ConditionValidation conditionValidation;
        if (null == field) {
            conditionValidation = instance.getClass().getAnnotation(ConditionValidation.class);
        } else if (null != value) {
            conditionValidation = field.getAnnotation(ConditionValidation.class);
        } else {
            conditionValidation = null;
        }

        if (null != conditionValidation) {
            ConditionRule[] rules = conditionValidation.rules();

            JexlEngine jexlEngine = new JexlEngine();
            JexlContext jexlContext = new ObjectContext<>(jexlEngine, instance);
            //jexlContext.set(conditionValidation.objectAlias(), instance);
            Arrays.stream(rules).forEach(conditionRule -> {
                Expression expression = jexlEngine.createExpression(conditionRule.rule());
                Boolean isValid = (Boolean) expression.evaluate(jexlContext);
                if (!isValid) {
                    ValidationError validationError = new ValidationError(conditionRule.errorDesc());
                    String errorDesc = conditionValidation.errorDesc() + " : " + conditionRule.errorDesc();
                    validationError.setErrorDescription(errorDesc);
                    errors.add(validationError);
                }
            });
        }
    }
}
