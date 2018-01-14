package entityvalidator.process;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import entityvalidator.ValidationError;
import entityvalidator.entity.FieldTest;
import entityvalidator.entity.User;

import static org.junit.Assert.*;

public class EntityFieldValidatorTest {

    @Test
    public void validateBean() throws Exception {

        FieldTest fieldTest = new FieldTest();
        EntityFieldValidator<FieldTest> entityFieldValidator = new EntityFieldValidator<>(fieldTest);

        List<ValidationError> validationErrors = new ArrayList<>();
        entityFieldValidator.validate(validationErrors);

        System.out.println(validationErrors.size());

    }

    @Test
    public void validateBeanAsField() throws Exception {

        FieldTest fieldTest = new FieldTest();
        User user = new User();
        user.setAge(20);
        fieldTest.setUser(user);

        Field field = fieldTest.getClass().getDeclaredField("user");

        EntityFieldValidator<FieldTest> entityFieldValidator = new EntityFieldValidator<>(field, fieldTest);

        List<ValidationError> validationErrors = new ArrayList<>();
        entityFieldValidator.validate(validationErrors);

        System.out.println(validationErrors.size());

    }
}