package entityvalidator.type.notation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import entityvalidator.type.CustomCollection;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CustomCollectionType {
    int maxLength() default 0;
    int minLength() default 0;
    boolean validateInnerEntity() default false;
    String errorDecs() default "Invalid Collection Type";
    Class<? extends CustomCollection> customCollectionClass();
}
