package entityvalidator.type.notation;

/*validate based on given conditions*/

import java.util.List;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
public @interface ConditionValidation {
    ConditionRule[] rules();
    String objectAlias() default "o";
    String errorDesc() default "Condition Failed";
}
