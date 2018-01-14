package entityvalidator.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import entityvalidator.type.CustomCollection;

public class UserList implements CustomCollection<User> {

    private List<User> users = new ArrayList<>();

    public void add(User user) {
        users.add(user);
    }

    @Override
    public Collection<User> get() {
        return users;
    }

    @Override
    public int size() {
        return users.size();
    }
}
