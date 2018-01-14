package entityvalidator.type.notation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import entityvalidator.type.DefaultEqualValidator;
import entityvalidator.type.EqualValidator;

/**
 * Validate for constant. can use for unique values.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Constant {
    String value();
    Class<? extends EqualValidator> equalValidator() default DefaultEqualValidator.class;
    String errorDesc() default "Invalid Value";
}
