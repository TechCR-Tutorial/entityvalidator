package entityvalidator.process;

import java.lang.reflect.Field;

import entityvalidator.type.notation.ValidatorFieldDescription;

public abstract class BaseFieldValidator<T> {

    protected Field field;
    protected T instance;
    protected Object value;
    protected String fieldName;


    public BaseFieldValidator(Field field, T entity) throws IllegalAccessException {
        this.field = field;
        this.instance = entity;
        process();
    }

    private void process() throws IllegalAccessException {
        if (null != field) {
            field.setAccessible(true);
            this.value = field.get(instance);
            fieldName = field.getName();
            if (field.isAnnotationPresent(ValidatorFieldDescription.class)) {
                ValidatorFieldDescription description = field.getAnnotation(ValidatorFieldDescription.class);
                fieldName = description.fieldDesc();
            }
        }
    }


}
