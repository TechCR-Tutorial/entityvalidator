package entityvalidator.process;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import entityvalidator.exception.UnsupportedFieldException;
import entityvalidator.type.notation.*;

public class FieldValidatorResolver {

    public <T> List<FieldValidator> getValidators(Field field, T entity) throws UnsupportedFieldException, IllegalAccessException {
        List<FieldValidator> validators = new ArrayList<>();
        field.setAccessible(true);
        if (field.isAnnotationPresent(EntityField.class)) {
            validators.add(new EntityFieldValidator(field, entity));
        } else {
            if (field.isAnnotationPresent(Mandatory.class)) {
                validators.add(new FieldMandatoryValidator(field, entity));
            }
            if (field.isAnnotationPresent(Length.class)) {
                validators.add(new FieldLengthValidator(field, entity));
            }
            if (field.isAnnotationPresent(LengthBetween.class)) {
                validators.add(new FieldLengthBetweenValidator(field, entity));
            }
            if (field.isAnnotationPresent(Constant.class)) {
                validators.add(new ConstantValidator(field, entity));
            }
            if (field.isAnnotationPresent(Range.class)) {
                validators.add(new FieldRangeValidator(field, entity));
            }
            if (field.isAnnotationPresent(NumberFormat.class)) {
                validators.add(new NumberFormatValidator(field, entity));
            }
            if (field.isAnnotationPresent(Regex.class)) {
                validators.add(new RegexValidator(field, entity));
            }
            if (field.isAnnotationPresent(ConditionValidation.class)) {
                validators.add(new ConditionValidator(field, entity));
            }

            if (field.isAnnotationPresent(CustomValidate.class)) {
                CustomValidate customValidate = field.getAnnotation(CustomValidate.class);
                Class<? extends CustomFieldValidator> validatorClass = customValidate.validator();
                try {
                    validators.add(validatorClass.
                            getDeclaredConstructor(Field.class, Object.class).
                            newInstance(field, entity));
                } catch (Exception e) {
                    throw new UnsupportedFieldException("Cannot Create " + validatorClass.getName() + " Instance");
                }
            }

            if (field.isAnnotationPresent(ArrayType.class)) {
                ArrayType arrayType = field.getAnnotation(ArrayType.class);
                validators.add(new CollectionTypeValidator(field, entity, arrayType));
            } else if (field.isAnnotationPresent(CollectionType.class)) {
                CollectionType collectionType = field.getAnnotation(CollectionType.class);
                validators.add(new CollectionTypeValidator(field, entity, collectionType));
            } else if (field.isAnnotationPresent(CustomCollectionType.class)) {
                CustomCollectionType customCollectionType = field.getAnnotation(CustomCollectionType.class);
                validators.add(new CollectionTypeValidator(field, entity, customCollectionType));
            }
        }
        return validators;
    }
}
