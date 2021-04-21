package com.cse364.infra;

import java.util.HashMap;
import com.cse364.domain.*;

public class InMemoryUserRepository implements UserRepository {
    private HashMap<Integer, User> users = new HashMap<>();

    InMemoryUserRepository() { }

    /**
     * Adds a user to the storage.
     */
    void add(User user) {
        users.put(user.getId(), user);
    }

    public User get(int id) {
        return users.get(id);
    }
}