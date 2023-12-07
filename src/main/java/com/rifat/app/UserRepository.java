package com.rifat.app;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static List<User> userTable = new ArrayList<>();

    static {
        userTable.add(new User(1, "user1", "user1", 2000));
        userTable.add(new User(2, "hacker", "hacker", 0));
        userTable.add(new User(3, "user3", "user3", 100));
    }

    public static List<User> getUserTable() {
        return userTable;
    }

    public static User getUserByUserNameAndPassword(String username, String password) {
        for (User user : userTable) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    public static User getUserById(int userId) {
        for (User user : userTable) {
            if (user.getId() == userId) {
                return user;
            }
        }
        return null;
    }

}
