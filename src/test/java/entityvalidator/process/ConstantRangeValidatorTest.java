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

public class ConstantRangeValidatorTest {
    @Test
    public void LongRageValidation() throws Exception {
        FieldTest fieldTest = new FieldTest();
        Field longValueRange = fieldTest.getClass().getDeclaredField("longRangeValue");

        //No validation
        //fieldTest.setLongRangeValue(101L);
        FieldRangeValidator fieldRangeValidator = new FieldRangeValidator(longValueRange, fieldTest);
        List<ValidationError> errors = new ArrayList<>();
        fieldRangeValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        //WithValidation
        errors = new ArrayList<>();
        fieldTest.setLongRangeValue(101L);
        fieldRangeValidator = new FieldRangeValidator(longValueRange, fieldTest);
        fieldRangeValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("fieldName",
                Matchers.equalTo("Range"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("errorDescription",
                Matchers.containsString("10"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("errorDescription",
                Matchers.containsString("100"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("actualValue",
                Matchers.equalTo("101"))));

        fieldTest.setLongRangeValue(-2L);
        fieldRangeValidator = new FieldRangeValidator(longValueRange, fieldTest);
        errors = new ArrayList<>();
        fieldRangeValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));

        fieldTest.setLongRangeValue(55L);
        fieldRangeValidator = new FieldRangeValidator(longValueRange, fieldTest);
        errors = new ArrayList<>();
        fieldRangeValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

    }
    @Test
    public void DoubleRangeValidation() throws Exception {
        FieldTest fieldTest = new FieldTest();
        Field longValueRange = fieldTest.getClass().getDeclaredField("doubleRangeValue");

        //No validation
        FieldRangeValidator fieldRangeValidator = new FieldRangeValidator(longValueRange, fieldTest);
        List<ValidationError> errors = new ArrayList<>();
        fieldRangeValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        //WithValidation
        errors = new ArrayList<>();
        fieldTest.setDoubleRangeValue(11.90);
        fieldRangeValidator = new FieldRangeValidator(longValueRange, fieldTest);
        fieldRangeValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("fieldName",
                Matchers.equalTo("doubleRangeValue"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("errorDescription",
                Matchers.containsString("3.25"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("errorDescription",
                Matchers.containsString("10.50"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("actualValue",
                Matchers.equalTo("11.9"))));

        fieldTest.setDoubleRangeValue(3.250);
        fieldRangeValidator = new FieldRangeValidator(longValueRange, fieldTest);
        errors = new ArrayList<>();
        fieldRangeValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setDoubleRangeValue(-4.45);
        fieldRangeValidator = new FieldRangeValidator(longValueRange, fieldTest);
        errors = new ArrayList<>();
        fieldRangeValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));

        fieldTest.setDoubleRangeValue(10.505);
        fieldRangeValidator = new FieldRangeValidator(longValueRange, fieldTest);
        errors = new ArrayList<>();
        fieldRangeValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));
    }
}
