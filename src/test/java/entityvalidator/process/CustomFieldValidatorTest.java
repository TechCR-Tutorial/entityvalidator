package entityvalidator.process;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Assert;
import org.junit.Test;

import entityvalidator.ValidationError;
import entityvalidator.Validator;
import entityvalidator.entity.FieldTest;
import entityvalidator.entity.User;

public class CustomFieldValidatorTest {
    @Test
    public void validate() throws Exception {
        User user = new User();
        user.setUserName("Chamly");
        user.setAge(10);
        user.setCustomValidateTest(null);

        Validator<User> validator = new Validator<>();
        validator.validate(user);
        List<ValidationError> errors = validator.getValidationErrors();
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("fieldName",
                Matchers.equalTo("customValidateTest"))));

        user.setCustomValidateTest(0);
        validator.validate(user);
        errors = validator.getValidationErrors();
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("fieldName",
                Matchers.equalTo("customValidateTest"))));

        user.setCustomValidateTest(20);
        validator.validate(user);
        errors = validator.getValidationErrors();
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));
    }
}