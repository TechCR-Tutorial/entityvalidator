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

public class RegexValidatorTest {

    @Test
    public void PositiveNumberRegexValidation() throws Exception {

        FieldTest fieldTest = new FieldTest();
        Field posNumber = fieldTest.getClass().getDeclaredField("posNumberRegex");

        //No validation
        RegexValidator regexValidator = new RegexValidator(posNumber, fieldTest);
        List<ValidationError> errors = new ArrayList<>();
        regexValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setPosNumberRegex(-12L);
        regexValidator = new RegexValidator(posNumber, fieldTest);
        errors = new ArrayList<>();
        regexValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "fieldName", Matchers.equalTo("Regex"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "errorDescription", CoreMatchers.containsString("Invalid data"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "errorDescription", CoreMatchers.containsString("^[0-9]*$"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "actualValue", CoreMatchers.containsString(fieldTest.getPosNumberRegex().toString()))));

        fieldTest.setPosNumberRegex(12L);
        regexValidator = new RegexValidator(posNumber, fieldTest);
        errors = new ArrayList<>();
        regexValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setPosNumberRegex(00L);
        regexValidator = new RegexValidator(posNumber, fieldTest);
        errors = new ArrayList<>();
        regexValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setPosNumberRegex(02L);
        regexValidator = new RegexValidator(posNumber, fieldTest);
        errors = new ArrayList<>();
        regexValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setPosNumberRegex(-0L);
        regexValidator = new RegexValidator(posNumber, fieldTest);
        errors = new ArrayList<>();
        regexValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setPosNumberRegex(10L);
        regexValidator = new RegexValidator(posNumber, fieldTest);
        errors = new ArrayList<>();
        regexValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setPosNumberRegex(-10L);
        regexValidator = new RegexValidator(posNumber, fieldTest);
        errors = new ArrayList<>();
        regexValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));

    }
    @Test
    public void NegativeNumberRegexValidation() throws Exception {

        FieldTest fieldTest = new FieldTest();
        Field negNumber = fieldTest.getClass().getDeclaredField("negNumberRegex");

        //No validation
        RegexValidator regexValidator = new RegexValidator(negNumber, fieldTest);
        List<ValidationError> errors = new ArrayList<>();
        regexValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setNegNumberRegex(12L);
        regexValidator = new RegexValidator(negNumber, fieldTest);
        errors = new ArrayList<>();
        regexValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "fieldName", Matchers.equalTo("negNumberRegex"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "errorDescription", CoreMatchers.containsString("Invalid data"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "errorDescription", CoreMatchers.containsString("^-[0-9]*$"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "actualValue", CoreMatchers.containsString(fieldTest.getNegNumberRegex().toString()))));

        fieldTest.setNegNumberRegex(-12L);
        regexValidator = new RegexValidator(negNumber, fieldTest);
        errors = new ArrayList<>();
        regexValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setNegNumberRegex(00L);
        regexValidator = new RegexValidator(negNumber, fieldTest);
        errors = new ArrayList<>();
        regexValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));

        fieldTest.setNegNumberRegex(-02L);
        regexValidator = new RegexValidator(negNumber, fieldTest);
        errors = new ArrayList<>();
        regexValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setNegNumberRegex(-0L);
        regexValidator = new RegexValidator(negNumber, fieldTest);
        errors = new ArrayList<>();
        regexValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));

        fieldTest.setNegNumberRegex(10L);
        regexValidator = new RegexValidator(negNumber, fieldTest);
        errors = new ArrayList<>();
        regexValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));

        fieldTest.setNegNumberRegex(-10L);
        regexValidator = new RegexValidator(negNumber, fieldTest);
        errors = new ArrayList<>();
        regexValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

    }
    @Test
    public void NonZeroStartRegexValidation() throws Exception {

        FieldTest fieldTest = new FieldTest();
        Field nonZeroNum = fieldTest.getClass().getDeclaredField("nonZeroPosNumberRegex");

        //No validation
        RegexValidator regexValidator = new RegexValidator(nonZeroNum, fieldTest);
        List<ValidationError> errors = new ArrayList<>();
        regexValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setNonZeroPosNumberRegex(00L);
        regexValidator = new RegexValidator(nonZeroNum, fieldTest);
        errors = new ArrayList<>();
        regexValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "fieldName", Matchers.equalTo("nonZeroPosNumberRegex"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "errorDescription", CoreMatchers.containsString("Invalid data"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "errorDescription", CoreMatchers.containsString("^[1-9][0-9]*$"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "actualValue", CoreMatchers.containsString(fieldTest.getNonZeroPosNumberRegex().toString()))));

        fieldTest.setNonZeroPosNumberRegex(-12L);
        regexValidator = new RegexValidator(nonZeroNum, fieldTest);
        errors = new ArrayList<>();
        regexValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));

        fieldTest.setNonZeroPosNumberRegex(12L);
        regexValidator = new RegexValidator(nonZeroNum, fieldTest);
        errors = new ArrayList<>();
        regexValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setNonZeroPosNumberRegex(-02L);
        regexValidator = new RegexValidator(nonZeroNum, fieldTest);
        errors = new ArrayList<>();
        regexValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));

        fieldTest.setNonZeroPosNumberRegex(-0L);
        regexValidator = new RegexValidator(nonZeroNum, fieldTest);
        errors = new ArrayList<>();
        regexValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));

        fieldTest.setNonZeroPosNumberRegex(10L);
        regexValidator = new RegexValidator(nonZeroNum, fieldTest);
        errors = new ArrayList<>();
        regexValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setNonZeroPosNumberRegex(-10L);
        regexValidator = new RegexValidator(nonZeroNum, fieldTest);
        errors = new ArrayList<>();
        regexValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));

    }
    @Test
    public void StringRegexValidation() throws Exception {

        FieldTest fieldTest = new FieldTest();
        Field stringRegex = fieldTest.getClass().getDeclaredField("stringRegex");

        //No validation
        RegexValidator regexValidator = new RegexValidator(stringRegex, fieldTest);
        List<ValidationError> errors = new ArrayList<>();
        regexValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setStringRegex("ABC123");
        errors = new ArrayList<>();
        regexValidator = new RegexValidator(stringRegex, fieldTest);
        regexValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "fieldName", Matchers.equalTo("stringRegex"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "errorDescription", CoreMatchers.containsString("Invalid data"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "errorDescription", CoreMatchers.containsString("[a-zA-Z]+"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "actualValue", CoreMatchers.containsString(fieldTest.getStringRegex()))));

        fieldTest.setStringRegex("ABC");
        errors = new ArrayList<>();
        regexValidator = new RegexValidator(stringRegex, fieldTest);
        regexValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setStringRegex("123");
        errors = new ArrayList<>();
        regexValidator = new RegexValidator(stringRegex, fieldTest);
        regexValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));

        fieldTest.setStringRegex("123ABC");
        errors = new ArrayList<>();
        regexValidator = new RegexValidator(stringRegex, fieldTest);
        regexValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));

        fieldTest.setStringRegex("abc");
        errors = new ArrayList<>();
        regexValidator = new RegexValidator(stringRegex, fieldTest);
        regexValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

    }
    @Test
    public void AnyStringRegexValidation() throws Exception {

        FieldTest fieldTest = new FieldTest();
        Field anyStringRegex = fieldTest.getClass().getDeclaredField("anyStringRegex");

        //No validation
        RegexValidator regexValidator = new RegexValidator(anyStringRegex, fieldTest);
        List<ValidationError> errors = new ArrayList<>();
        regexValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setAnyStringRegex("ABC123");
        errors = new ArrayList<>();
        regexValidator = new RegexValidator(anyStringRegex, fieldTest);
        regexValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setAnyStringRegex("ABC");
        errors = new ArrayList<>();
        regexValidator = new RegexValidator(anyStringRegex, fieldTest);
        regexValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setAnyStringRegex("123");
        errors = new ArrayList<>();
        regexValidator = new RegexValidator(anyStringRegex, fieldTest);
        regexValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setAnyStringRegex("123ABC");
        errors = new ArrayList<>();
        regexValidator = new RegexValidator(anyStringRegex, fieldTest);
        regexValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setAnyStringRegex("abc");
        errors = new ArrayList<>();
        regexValidator = new RegexValidator(anyStringRegex, fieldTest);
        regexValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setAnyStringRegex("%^");
        errors = new ArrayList<>();
        regexValidator = new RegexValidator(anyStringRegex, fieldTest);
        regexValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

    }
}
