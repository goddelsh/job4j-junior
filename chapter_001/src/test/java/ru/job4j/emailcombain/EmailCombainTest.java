package ru.job4j.emailcombain;


import org.junit.Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class EmailCombainTest {

    @Test
    public void combain() {
        EmailCombain combain = new EmailCombain();
        User user1 = new User("user1", new ArrayList<String>(Arrays.asList("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru")));
        User user2 = new User("user2", new ArrayList<String>(Arrays.asList("foo@gmail.com", "ups@pisem.net")));
        User user3 = new User("user3", new ArrayList<String>(Arrays.asList("xyz@pisem.net", "vasya@pupkin.com")));
        User user4 = new User("user4", new ArrayList<String>(Arrays.asList("ups@pisem.net", "aaa@bbb.ru")));
        User user5 = new User("user5", new ArrayList<String>(Arrays.asList("xyz@pisem.net")));
        List<User> result = combain.combain(List.of(user1, user2, user3, user4, user5));
        System.out.println(result);
    }
}
