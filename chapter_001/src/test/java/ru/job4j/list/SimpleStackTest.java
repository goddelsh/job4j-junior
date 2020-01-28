package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleStackTest {

    public SimpleStack stack;

    @Before
    public void init() {
        stack = new SimpleStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
    }

    @Test
    public void poll() {
        assertThat(stack.poll(), is(3));
        assertThat(stack.poll(), is(2));
        assertThat(stack.poll(), is(1));
    }


}