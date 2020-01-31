package ru.job4j.map;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void addUserToMap() {
        Calendar calendar = new GregorianCalendar(1900, 0 , 25);
        User user1 = new User("user", 1, calendar);
        User user2 = new User("user",1, calendar);

        assertThat(user1.equals(user2), is(true));

        Map<User, Object> map = new HashMap<User, Object>();
        map.put(user1, "first");
        map.put(user2, "second");
        System.out.println(map);
    }

}