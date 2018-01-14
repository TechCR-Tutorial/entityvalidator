package entityvalidator.type;

public class NumberValueConvertor implements ValueConvertor<Number> {

    @Override
    public Number convert(String value) {
        return Double.valueOf(value);
    }
}
