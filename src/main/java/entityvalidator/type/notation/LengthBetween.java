package entityvalidator.type.notation;

import entityvalidator.type.LengthCriteriaType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface LengthBetween {
    int minLength();
    int maxLength();
    LengthCriteriaType minLengthCriteria () default LengthCriteriaType.GREATER_THAN_OR_EQUAL;
    LengthCriteriaType maxLengthCriteria () default LengthCriteriaType.LESS_THAN_OR_EQUAL;
    String errorDesc() default "Invalid Between Length";
}
