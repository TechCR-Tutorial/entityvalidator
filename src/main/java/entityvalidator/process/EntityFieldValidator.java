package entityvalidator.process;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import entityvalidator.ValidationError;
import entityvalidator.exception.UnsupportedFieldException;
import entityvalidator.type.notation.ConditionValidation;
import entityvalidator.type.notation.ExcludeParent;
import entityvalidator.type.notation.ValidatorFieldDescription;

public class EntityFieldValidator<T> implements FieldValidator {

    private T bean;
    private String fieldName;

    public EntityFieldValidator(T bean) {
        this.bean = bean;
    }

    public EntityFieldValidator(Field field, Object parentEntity) throws IllegalAccessException {
        field.setAccessible(true);
        this.bean = (T) field.get(parentEntity);
        if (field.isAnnotationPresent(ValidatorFieldDescription.class)) {
            ValidatorFieldDescription description = field.getAnnotation(ValidatorFieldDescription.class);
            this.fieldName = description.fieldDesc();
        }
    }

    @Override
    public void validate(List<ValidationError> errors) throws UnsupportedFieldException, IllegalAccessException {

        if (null != bean) {
            List<Field> fields;
            if (bean.getClass().isAnnotationPresent(ExcludeParent.class)) {
                fields = Arrays.asList(bean.getClass().getDeclaredFields());
            } else {
                fields = new ArrayList<>();
                getInheritedFields(bean.getClass(), fields);
            }

            if (bean.getClass().isAnnotationPresent(ConditionValidation.class)) {
                ConditionValidator conditionValidator = new ConditionValidator(bean);
                conditionValidator.validate(errors);
            }

            FieldValidatorResolver validatorResolver = new FieldValidatorResolver();
            for (Field field : fields) {
                List<FieldValidator> validators = validatorResolver.getValidators(field, bean);
                for (FieldValidator validator : validators) {
                    validator.validate(errors);
                }
            }
        }
    }

    private void getInheritedFields(Class<?> clazz, List<Field> fields) {

        if (clazz != Object.class) {
            for (Field field : clazz.getDeclaredFields()) {
                if (!field.isSynthetic()) {
                    fields.add(field);
                }
            }
            getInheritedFields(clazz.getSuperclass(), fields);
        }
    }
}
