package entityvalidator.entity;

import java.util.Objects;

import entityvalidator.type.NumberEqualValidator;
import entityvalidator.type.notation.Constant;
import entityvalidator.type.notation.CustomValidate;
import entityvalidator.type.notation.Mandatory;

public class User {

    @Mandatory
    private String userName;
    @Constant(value = "10", equalValidator = NumberEqualValidator.class)
    private int age;
    @CustomValidate(validator = TestCustomValidator.class)
    private Integer customValidateTest = 20;

    public User() {
    }

    public User(int age) {
        this.age = age;
    }

    public Integer getCustomValidateTest() {
        return customValidateTest;
    }

    public void setCustomValidateTest(Integer customValidateTest) {
        this.customValidateTest = customValidateTest;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age &&
                Objects.equals(userName, user.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, age);
    }
}
