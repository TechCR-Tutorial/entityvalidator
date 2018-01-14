package entityvalidator.type.notation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import entityvalidator.process.CustomFieldValidator;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CustomValidate {
    Class<? extends CustomFieldValidator> validator();
    String errorDesc() default "Invalid Value";
}
