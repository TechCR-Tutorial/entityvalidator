package entityvalidator;

public class ValidatorTest {
//    @Test
//    public void validateBasic() throws Exception {
//        FieldTest user = new FieldTest();
//
//        //Empty Name and Address
//        //Check with field description. Error description.
//        Validator<FieldTest> validator = new Validator<>();
//        validator.validate(user);
//
//        List<ValidationError> errors = validator.getValidationErrors();
//        Assert.assertThat(errors, IsCollectionWithSize.hasSize(2));
//
//        ValidationError mandatoryNameError = new ValidationError("Name", "Mandatory field", "");
//        ValidationError mandatoryAddressError = new ValidationError("address", "Address Mandatory", "");
//
//        Assert.assertThat(errors, Matchers.containsInAnyOrder(mandatoryNameError, mandatoryAddressError));
//
//        //Valid name and address but with age length is 1
//        user.setName("Chamly");
//        user.setAddress("Colombo");
//        user.setEqualProperty("1");
//
//        validator = new Validator<>();
//        validator.validate(user);
//
//        errors = validator.getValidationErrors();
//        Assert.assertThat(errors, IsCollectionWithSize.hasSize(1));
//
//        ValidationError fieldLenghtAge = errors.get(0);
//        Assert.assertEquals(fieldLenghtAge.getFieldName(), "Age");
//        Assert.assertThat(fieldLenghtAge.getErrorDescription(), Matchers.startsWith("Invalid Length"));
//        Assert.assertEquals(fieldLenghtAge.getActualValue(), "1");
//
//
//
//        /*Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
//                "fieldName", Matchers.equalTo("age"))));
//        Assert.assertThat(errors, CoreMatchers.hasItem(Matchers.<ValidationError>hasProperty(
//                "errorDescription", Matchers.startsWith("Invalid Length"))));
//        */



    //}
}