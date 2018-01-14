package entityvalidator.process;

import entityvalidator.ValidationError;
import entityvalidator.entity.FieldTest;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class NumberFormatValidatorTest {

    @Test
    public void doubleNumberFormatValidation () throws Exception {

        FieldTest fieldTest = new FieldTest();
        Field doubleNumber = fieldTest.getClass().getDeclaredField("averageScore");

        //No validation
        NumberFormatValidator numberFormatValidator = new NumberFormatValidator(doubleNumber, fieldTest);

        List<ValidationError> errors = new ArrayList<>();
        numberFormatValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setAverageScore(123.45);
        errors = new ArrayList<>();
        numberFormatValidator = new NumberFormatValidator(doubleNumber, fieldTest);
        numberFormatValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("fieldName",
                Matchers.equalTo("Average"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("errorDescription",
                Matchers.containsString("##.###"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("actualValue",
                Matchers.equalTo("123.45"))));

        fieldTest.setAverageScore(12.345);
        errors = new ArrayList<>();
        numberFormatValidator = new NumberFormatValidator(doubleNumber, fieldTest);
        numberFormatValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

    }
}
