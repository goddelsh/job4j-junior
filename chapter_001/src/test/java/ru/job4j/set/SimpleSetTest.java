package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    public SimpleSet<Integer> simpleSet;

    @Before
    public void init() {
        simpleSet = new SimpleSet<Integer>();
        simpleSet.add(1);
        simpleSet.add(2);
        simpleSet.add(3);
    }

    @Test
    public void testSetPropeties() {
        assertThat(simpleSet.add(2), is(false));
        assertThat(simpleSet.add(1), is(false));
        assertThat(simpleSet.size(), is(3));
    }

    @Test
    public void whenAddedFourShouldFindIt() {
        simpleSet.add(4);
        assertThat(simpleSet.contains(4), is(true));
    }
}