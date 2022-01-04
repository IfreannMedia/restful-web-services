package com.freann.rest.webservices.restfulwebservices.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();

    private static int usersCounter = 3;

    static {
        users.add(new User(1, "Mario", new Date()));
        users.add(new User(2, "Luigi", new Date()));
        users.add(new User(3, "Wario", new Date()));
    }

    public List<User> findAll() {
        return users;
    }

    public User saveUser(User user) {
        if (user.getId() == null) {
            user.setId(++usersCounter);
        }
        users.add(user);
        return user;
    }

    public User findById(Integer id) {
        return users.stream().filter(user -> Objects.equals(user.getId(), id)).findFirst().orElse(null);
    }
}
