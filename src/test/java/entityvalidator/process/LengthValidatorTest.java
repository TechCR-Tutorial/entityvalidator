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
import entityvalidator.entity.FieldTest;
import entityvalidator.exception.UnsupportedFieldException;
import entityvalidator.type.LengthCriteriaType;

public class LengthValidatorTest {
    @Test
    public void validateStringLength() throws Exception {
        FieldTest fieldTest = new FieldTest();
        Field ageField = fieldTest.getClass().getDeclaredField("equalProperty");

        //Empty age, no validations
        FieldLengthValidator fieldLengthValidator = new FieldLengthValidator(ageField, fieldTest);
        List<ValidationError> errors = new ArrayList<>();
        fieldLengthValidator.validate(errors);


        //Invalid length with field description.
        fieldTest.setEqualProperty("1");

        fieldLengthValidator = new FieldLengthValidator(ageField, fieldTest);
        fieldLengthValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "fieldName", Matchers.equalTo("Equal Prop"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "errorDescription", Matchers.startsWith("Invalid Length"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "errorDescription", CoreMatchers.containsString(LengthCriteriaType.EQAUL.name()))));

        //With valid age
        fieldTest.setEqualProperty("12");

        errors = new ArrayList<>();
        fieldLengthValidator = new FieldLengthValidator(ageField, fieldTest);
        fieldLengthValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

    }

    @Test
    public void validateLongLength() throws Exception {
        FieldTest fieldTest = new FieldTest();
        Field heightField = fieldTest.getClass().getDeclaredField("longProperty");
        fieldTest.setLongProperty(new Long(12));

        FieldLengthValidator fieldLengthValidator = new FieldLengthValidator(heightField, fieldTest);
        List<ValidationError> errors = new ArrayList<>();
        fieldLengthValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setLongProperty(new Long(123));

        fieldLengthValidator = new FieldLengthValidator(heightField, fieldTest);
        errors = new ArrayList<>();
        fieldLengthValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "fieldName", Matchers.equalTo("longProperty"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "errorDescription", CoreMatchers.containsString("2"))));

        fieldTest.setLongProperty(124l);

        fieldLengthValidator = new FieldLengthValidator(heightField, fieldTest);
        errors = new ArrayList<>();
        fieldLengthValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "fieldName", Matchers.equalTo("longProperty"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "errorDescription", CoreMatchers.containsString("2"))));

        fieldTest.setLongProperty(12l);

        fieldLengthValidator = new FieldLengthValidator(heightField, fieldTest);
        errors = new ArrayList<>();
        fieldLengthValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));
    }


    @Test(expected = UnsupportedFieldException.class)
    public void validateDoubleWrapLength() throws Exception {
        FieldTest fieldTest = new FieldTest();
        Field averageField = fieldTest.getClass().getDeclaredField("doubleValiateProperty");
        fieldTest.setDoubleValiateProperty(new Double(1.2));

        //Empty age, no validations
        FieldLengthValidator fieldLengthValidator = new FieldLengthValidator(averageField, fieldTest);
        List<ValidationError> errors = new ArrayList<>();
        fieldLengthValidator.validate(errors);


    }

    @Test
    public void validateGreaterThanOrEqualCriteriaType() throws Exception {
        FieldTest fieldTest = new FieldTest();
        Field salaryField = fieldTest.getClass().getDeclaredField("gtoqProperty");

        FieldLengthValidator fieldLengthValidator = new FieldLengthValidator(salaryField, fieldTest);

        List<ValidationError> errors = new ArrayList<>();
        fieldLengthValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setGtoqProperty("12");
        fieldLengthValidator = new FieldLengthValidator(salaryField, fieldTest);
        errors = new ArrayList<>();
        fieldLengthValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "fieldName", Matchers.equalTo("gtoqProperty"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "errorDescription", CoreMatchers.containsString("4"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "errorDescription", CoreMatchers.containsString(LengthCriteriaType.GREATER_THAN_OR_EQUAL.name()))));


        fieldTest.setGtoqProperty("1234");
        fieldLengthValidator = new FieldLengthValidator(salaryField, fieldTest);
        errors = new ArrayList<>();
        fieldLengthValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setGtoqProperty("123456");
        fieldLengthValidator = new FieldLengthValidator(salaryField, fieldTest);
        errors = new ArrayList<>();
        fieldLengthValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

    }

    @Test
    public void validateGreaterThanCriteriaType() throws Exception {
        FieldTest fieldTest = new FieldTest();
        Field salaryField = fieldTest.getClass().getDeclaredField("gtProperty");

        FieldLengthValidator fieldLengthValidator = new FieldLengthValidator(salaryField, fieldTest);

        List<ValidationError> errors = new ArrayList<>();
        fieldLengthValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setGtProperty("12");
        fieldLengthValidator = new FieldLengthValidator(salaryField, fieldTest);
        errors = new ArrayList<>();
        fieldLengthValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "fieldName", Matchers.equalTo("gtProperty"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "errorDescription", CoreMatchers.containsString("4"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "errorDescription", CoreMatchers.containsString(LengthCriteriaType.GREATER_THAN.name()))));


        fieldTest.setGtProperty("1234");
        fieldLengthValidator = new FieldLengthValidator(salaryField, fieldTest);
        errors = new ArrayList<>();
        fieldLengthValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));

        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "fieldName", Matchers.equalTo("gtProperty"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "errorDescription", CoreMatchers.containsString("4"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "errorDescription", CoreMatchers.containsString(LengthCriteriaType.GREATER_THAN.name()))));

        fieldTest.setGtProperty("123456");
        fieldLengthValidator = new FieldLengthValidator(salaryField, fieldTest);
        errors = new ArrayList<>();
        fieldLengthValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

    }

    @Test
    public void validateLessThanOrEqualCriteriaType() throws Exception {
        FieldTest fieldTest = new FieldTest();
        Field salaryField = fieldTest.getClass().getDeclaredField("ltoqProperty");

        FieldLengthValidator fieldLengthValidator = new FieldLengthValidator(salaryField, fieldTest);

        List<ValidationError> errors = new ArrayList<>();
        fieldLengthValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setLtoqProperty("12345");
        fieldLengthValidator = new FieldLengthValidator(salaryField, fieldTest);
        errors = new ArrayList<>();
        fieldLengthValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "fieldName", Matchers.equalTo("ltoqProperty"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "errorDescription", CoreMatchers.containsString("4"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "errorDescription", CoreMatchers.containsString(LengthCriteriaType.LESS_THAN_OR_EQUAL.name()))));


        fieldTest.setLtoqProperty("1234");
        fieldLengthValidator = new FieldLengthValidator(salaryField, fieldTest);
        errors = new ArrayList<>();
        fieldLengthValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setLtoqProperty("123");
        fieldLengthValidator = new FieldLengthValidator(salaryField, fieldTest);
        errors = new ArrayList<>();
        fieldLengthValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

    }


    @Test
    public void validateLessThanCriteriaType() throws Exception {
        FieldTest fieldTest = new FieldTest();
        Field salaryField = fieldTest.getClass().getDeclaredField("ltProperty");

        FieldLengthValidator fieldLengthValidator = new FieldLengthValidator(salaryField, fieldTest);

        List<ValidationError> errors = new ArrayList<>();
        fieldLengthValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        fieldTest.setLtProperty("12345");
        fieldLengthValidator = new FieldLengthValidator(salaryField, fieldTest);
        errors = new ArrayList<>();
        fieldLengthValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "fieldName", Matchers.equalTo("ltProperty"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "errorDescription", CoreMatchers.containsString("4"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "errorDescription", CoreMatchers.containsString(LengthCriteriaType.LESS_THAN.name()))));


        fieldTest.setLtProperty("1234");
        fieldLengthValidator = new FieldLengthValidator(salaryField, fieldTest);
        errors = new ArrayList<>();
        fieldLengthValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));

        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "fieldName", Matchers.equalTo("ltProperty"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "errorDescription", CoreMatchers.containsString("4"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "errorDescription", CoreMatchers.containsString(LengthCriteriaType.LESS_THAN.name()))));

        fieldTest.setLtProperty("123");
        fieldLengthValidator = new FieldLengthValidator(salaryField, fieldTest);
        errors = new ArrayList<>();
        fieldLengthValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

    }
}