package entityvalidator.type.notation;

import entityvalidator.type.ValueConvertor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*Validate Ranges
* specific for Numbers*/

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Range {
    String minValue();
    String maxValue();
    String errorDesc() default "Invalid Range";
}
