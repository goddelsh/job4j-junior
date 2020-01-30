package ru.job4j.map;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void addUserToMap() {
        User user1 = new User("user");
        User user2 = new User("user");

        Map<User, Object> map = Map.of(user1, "first", user2, "second");

        System.out.println(map);
    }

}