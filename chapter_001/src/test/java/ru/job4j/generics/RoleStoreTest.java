package ru.job4j.generics;

import org.junit.Test;


import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.CoreMatchers.nullValue;

public class RoleStoreTest {


    @Test
    public void replace() {
        RoleStore roleStore = new RoleStore(5);
        roleStore.add(new Role("1"));
        roleStore.add(new Role("2"));
        roleStore.add(new Role("3"));
        var replacementObject = new Role("4");
        roleStore.replace("2", replacementObject);
        var result = roleStore.findById("4");
        assertThat(result, is(replacementObject));
    }

    @Test
    public void delete() {
        RoleStore roleStore = new RoleStore(5);
        roleStore.add(new Role("1"));
        roleStore.add(new Role("2"));
        roleStore.add(new Role("3"));
        roleStore.delete("2");
        var result = roleStore.findById("2");
        assertThat(result, is(nullValue()));
    }


}
