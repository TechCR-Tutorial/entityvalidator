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

public class LengthBetweenValidatorTest {

    @Test
    public void validateStringLengthBetween() throws Exception {

        FieldTest fieldTest = new FieldTest();
        Field lengthBetween = fieldTest.getClass().getDeclaredField("lenthPropery");

        FieldLengthBetweenValidator fieldLengthBetweenValidator = new FieldLengthBetweenValidator(lengthBetween, fieldTest);

        // Before adding validation
        List<ValidationError> errors = new ArrayList<>();
        fieldLengthBetweenValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setLenthPropery("123");
        fieldLengthBetweenValidator =  new FieldLengthBetweenValidator(lengthBetween, fieldTest);
        errors = new ArrayList<>();
        fieldLengthBetweenValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("fieldName",
                Matchers.equalTo("Between length"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("errorDescription",
                Matchers.containsString("5"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("errorDescription",
                Matchers.containsString("10"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("actualValue",
                Matchers.equalTo("123"))));

        fieldTest.setLenthPropery("12345");
        fieldLengthBetweenValidator = new FieldLengthBetweenValidator(lengthBetween, fieldTest);
        errors = new ArrayList<>();
        fieldLengthBetweenValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setLenthPropery("123456");
        fieldLengthBetweenValidator = new FieldLengthBetweenValidator(lengthBetween, fieldTest);
        errors = new ArrayList<>();
        fieldLengthBetweenValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setLenthPropery("12345678900");
        fieldLengthBetweenValidator = new FieldLengthBetweenValidator(lengthBetween, fieldTest);
        errors = new ArrayList<>();
        fieldLengthBetweenValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));
    }

    @Test
    public void LongLengthBetween () throws Exception {

        FieldTest fieldTest = new FieldTest();
        Field lengthBetween = fieldTest.getClass().getDeclaredField("numberLengthProperty");

        fieldTest.setNumberLengthProperty(123L);
        FieldLengthBetweenValidator fieldLengthBetweenValidator =  new FieldLengthBetweenValidator(lengthBetween, fieldTest);
        List<ValidationError> errors = new ArrayList<>();
        fieldLengthBetweenValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("fieldName",
                Matchers.equalTo("numberLengthProperty"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("errorDescription",
                Matchers.containsString("5"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("errorDescription",
                Matchers.containsString("10"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("actualValue",
                Matchers.equalTo("123"))));

        fieldTest.setNumberLengthProperty(12345L);
        fieldLengthBetweenValidator = new FieldLengthBetweenValidator(lengthBetween, fieldTest);
        errors = new ArrayList<>();
        fieldLengthBetweenValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setNumberLengthProperty(123456L);
        fieldLengthBetweenValidator = new FieldLengthBetweenValidator(lengthBetween, fieldTest);
        errors = new ArrayList<>();
        fieldLengthBetweenValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setNumberLengthProperty(12345678900L);
        fieldLengthBetweenValidator = new FieldLengthBetweenValidator(lengthBetween, fieldTest);
        errors = new ArrayList<>();
        fieldLengthBetweenValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));
    }
}
