package entityvalidator.type;

@FunctionalInterface
public interface EqualValidator<T> {
    boolean isEqual(String constantValue, T actualValue);
}
