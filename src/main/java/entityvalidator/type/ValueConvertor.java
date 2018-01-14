package entityvalidator.type;

@FunctionalInterface
public interface ValueConvertor<T> {
    T convert(String value);
}
