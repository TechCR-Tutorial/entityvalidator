package entityvalidator.process;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.hamcrest.collection.IsCollectionWithSize;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import entityvalidator.ValidationError;
import entityvalidator.entity.FieldTest;
import entityvalidator.entity.User;
import entityvalidator.type.notation.ArrayType;

import static org.junit.Assert.*;

public class CollectionTypeArrayValidatorTest {

    @Test
    public void arrayWithoutInternalValidation() throws Exception {
        FieldTest fieldTest = new FieldTest();
        Field field = fieldTest.getClass().getDeclaredField("usersArrayWithoutInternalValidation");

        ArrayType arrayType = Mockito.mock(ArrayType.class);
        Mockito.when(arrayType.maxLength()).thenReturn(1);
        Mockito.when(arrayType.minLength()).thenReturn(0);

        //Empty User array
        CollectionTypeValidator collectionTypeValidator = new CollectionTypeValidator(field, fieldTest, arrayType);
        List<ValidationError> errors = new ArrayList<>();
        collectionTypeValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        //Array minimum size 1 with empty array
        Mockito.when(arrayType.minLength()).thenReturn(1);
        collectionTypeValidator = new CollectionTypeValidator(field, fieldTest, arrayType);
        collectionTypeValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));

        //Array minimum size 0, maximum size 1 with size array 1
        Mockito.when(arrayType.minLength()).thenReturn(0);
        errors = new ArrayList<>();
        User[] userArray = new User[1];
        fieldTest.setUsersArrayWithoutInternalValidation(userArray);
        collectionTypeValidator = new CollectionTypeValidator(field, fieldTest, arrayType);
        collectionTypeValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        //Array minimum size 0, maximum size 1 with size array 2
        userArray = new User[2];
        fieldTest.setUsersArrayWithoutInternalValidation(userArray);
        collectionTypeValidator = new CollectionTypeValidator(field, fieldTest, arrayType);
        collectionTypeValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));

    }

    @Test
    public void arrayWithInternalValidation() throws Exception {
        FieldTest fieldTest = new FieldTest();
        Field field = fieldTest.getClass().getDeclaredField("usersArrayWithInternalValidation");


        ArrayType arrayType = Mockito.mock(ArrayType.class);
        Mockito.when(arrayType.validateInnerEntity()).thenReturn(true);
        Mockito.when(arrayType.maxLength()).thenReturn(1);
        Mockito.when(arrayType.minLength()).thenReturn(0);
        //Mockito.when(arrayType.errorDecs()).thenReturn("Invalid Collection Type");

        //Array minimum size 0, maximum 1 with empty array. - no errors
        User[] users = new User[0];
        fieldTest.setUsersArrayWithInternalValidation(users);
        CollectionTypeValidator collectionTypeValidator = new CollectionTypeValidator(field, fieldTest, arrayType);
        List<ValidationError> errors = new ArrayList<>();
        collectionTypeValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

        //user with invalid name- name required
        //array minimum size 0 and mx size 1- max item exceed.
        User user = new User();
        users = new User[2];
        users[0] = user;
        fieldTest.setUsersArrayWithInternalValidation(users);
        collectionTypeValidator = new CollectionTypeValidator(field, fieldTest, arrayType);
        errors = new ArrayList<>();
        collectionTypeValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("fieldName",
                Matchers.equalTo("usersArrayWithInternalValidation"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("errorDescription",
                Matchers.equalTo("Max Item exceed"))));

        Mockito.when(arrayType.minLength()).thenReturn(4);
        Mockito.when(arrayType.maxLength()).thenReturn(4);
        collectionTypeValidator = new CollectionTypeValidator(field, fieldTest, arrayType);
        errors = new ArrayList<>();
        collectionTypeValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("fieldName",
                Matchers.equalTo("usersArrayWithInternalValidation"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("errorDescription",
                Matchers.equalTo("Minimum Item not covered"))));

        //No max / min length erros. User array size 1- but erros in User object
        Mockito.when(arrayType.minLength()).thenReturn(1);
        Mockito.when(arrayType.maxLength()).thenReturn(4);
        collectionTypeValidator = new CollectionTypeValidator(field, fieldTest, arrayType);
        errors = new ArrayList<>();
        collectionTypeValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(2));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("fieldName",
                Matchers.equalTo("userName"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("errorDescription",
                Matchers.equalTo("Mandatory field"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("fieldName",
                Matchers.equalTo("age"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("errorDescription",
                Matchers.equalTo("Invalid Value:10"))));

        //No max / min length errors. User array size 2- but erros in User object
        User usertwo = new User();
        users[1] = usertwo;
        collectionTypeValidator = new CollectionTypeValidator(field, fieldTest, arrayType);
        errors = new ArrayList<>();
        collectionTypeValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(4));
        collectionTypeValidator.validate(errors);
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("fieldName",
                Matchers.equalTo("userName"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("errorDescription",
                Matchers.equalTo("Mandatory field"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("fieldName",
                Matchers.equalTo("age"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("errorDescription",
                Matchers.equalTo("Invalid Value:10"))));


        //No max / min length errors. User array size 2 - one user have errors
        user.setAge(10);
        user.setUserName("Chamly One");
        collectionTypeValidator = new CollectionTypeValidator(field, fieldTest, arrayType);
        errors = new ArrayList<>();
        collectionTypeValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(2));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("fieldName",
                Matchers.equalTo("userName"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("errorDescription",
                Matchers.equalTo("Mandatory field"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("fieldName",
                Matchers.equalTo("age"))));
        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.hasProperty("errorDescription",
                Matchers.equalTo("Invalid Value:10"))));

        //No max / min length errors. User array size 2 - no user have errors
        usertwo.setAge(10);
        usertwo.setUserName("Chamly Two");
        collectionTypeValidator = new CollectionTypeValidator(field, fieldTest, arrayType);
        errors = new ArrayList<>();
        collectionTypeValidator.validate(errors);
        Assert.assertThat(errors, IsCollectionWithSize.hasSize(0));

    }
}