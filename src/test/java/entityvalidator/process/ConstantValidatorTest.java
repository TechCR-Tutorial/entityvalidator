package entityvalidator.process;

import entityvalidator.ValidationError;
import entityvalidator.entity.FieldTest;
import entityvalidator.type.notation.Constant;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ConstantValidatorTest {
    @Test
    public void NumberConstantValidation() throws Exception {

        FieldTest fieldTest = new FieldTest();
        Field doubleNumber = fieldTest.getClass().getDeclaredField("doubleNumber");

        //No validation
        ConstantValidator constantValidator = new ConstantValidator(doubleNumber, fieldTest);
        List<ValidationError> errors = new ArrayList<>();
        constantValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        //withValidation
        fieldTest.setDoubleNumber("3.14");
        errors = new ArrayList<>();
        constantValidator = new ConstantValidator(doubleNumber, fieldTest);
        constantValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        //with Validation error
        fieldTest.setDoubleNumber("3.15");
        errors = new ArrayList<>();
        constantValidator = new ConstantValidator(doubleNumber, fieldTest);
        constantValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("fieldName",
                Matchers.equalTo("Number Constant"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("errorDescription",
                Matchers.containsString(""))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("actualValue",
                Matchers.equalTo("3.15"))));
    }
}
