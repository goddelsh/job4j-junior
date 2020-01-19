package ru.job4j.generics;



import org.junit.Test;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class UserStoreTest {
    @Test
    public void replace() {
        UserStore userStore = new UserStore(5);
        userStore.add(new User("1"));
        userStore.add(new User("2"));
        userStore.add(new User("3"));
        var replacementObject = new User("4");
        userStore.replace("2", replacementObject);
        var result = userStore.findById("4");
        assertThat(result, is(replacementObject));
    }

    @Test
    public void delete() {
        UserStore userStore = new UserStore(5);
        userStore.add(new User("1"));
        userStore.add(new User("2"));
        userStore.add(new User("3"));
        userStore.delete("2");
        var result = userStore.findById("2");
        assertThat(result, is(nullValue()));
    }

}
