package entityvalidator.type;

import java.util.Collection;

public interface CustomCollection<T> {
    Collection<T> get();
    int size();
}
