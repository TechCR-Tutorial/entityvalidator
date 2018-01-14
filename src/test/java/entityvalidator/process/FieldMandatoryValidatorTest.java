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

public class FieldMandatoryValidatorTest {

    @Test
    public void validateForNullWithFieldDescription() throws Exception {
        FieldTest fieldTest = new FieldTest();
        Field field = fieldTest.getClass().getDeclaredField("name");
        List<ValidationError> errors = new ArrayList<>();
        FieldMandatoryValidator mandatoryValidator = new FieldMandatoryValidator(field, fieldTest);
        mandatoryValidator.validate(errors);

        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "fieldName", Matchers.equalTo("Name"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "errorDescription", Matchers.equalTo("Mandatory field"))));


        fieldTest.setName("Chamly");
        errors = new ArrayList<>();
        mandatoryValidator = new FieldMandatoryValidator(field, fieldTest);
        mandatoryValidator.validate(errors);

        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

    }

    @Test
    public void validateForNullWithErrorDescription() throws Exception {
        FieldTest fieldTest = new FieldTest();
        Field addressField = fieldTest.getClass().getDeclaredField("address");
        List<ValidationError> errors = new ArrayList<>();
        FieldMandatoryValidator mandatoryValidator = new FieldMandatoryValidator(addressField, fieldTest);
        mandatoryValidator.validate(errors);

        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "fieldName", Matchers.equalTo("address"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
                "errorDescription", Matchers.equalTo("Address Mandatory"))));


        fieldTest.setAddress("Colombo");
        errors = new ArrayList<>();
        mandatoryValidator = new FieldMandatoryValidator(addressField, fieldTest);
        mandatoryValidator.validate(errors);

        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

    }
}