package entityvalidator.type;

public class DefaultEqualValidator implements EqualValidator<String> {

    @Override
    public boolean isEqual(String constantValue, String actualValue) {
        return null == constantValue || constantValue.equals(actualValue);
    }
}
