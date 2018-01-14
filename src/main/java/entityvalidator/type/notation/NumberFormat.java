package entityvalidator.type.notation;

import entityvalidator.type.LengthCriteriaType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Check for number format.
 * specially for double, float.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface NumberFormat {
    String numberFormat();
    LengthCriteriaType lengthCriteria() default LengthCriteriaType.EQAUL;
    String errorDesc() default "Invalid Number Format";
}
