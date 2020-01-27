package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class DynamicLinkedContainerTest {

    DynamicLinkedContainer lc;

    @Before
    public void init() {
        lc = new DynamicLinkedContainer();
        lc.add(1);
        lc.add(2);
        lc.add(3);
    }

    @Test
    public void addAndGetTest() {
        assertThat(lc.get(0), is(1));
        assertThat(lc.get(1), is(2));
        assertThat(lc.get(2), is(3));
    }


    @Test
    public void iteratorTest() {
        var it = lc.iterator();
        it.hasNext();
        it.hasNext();
        it.next();
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(false));
    }
}