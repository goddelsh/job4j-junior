package ru.job4j.generics;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class SimpleArrayTest {

    private SimpleArray<String> testingArray;

    @Before
    public void initTestingArray() {
        testingArray = new SimpleArray(10);
        testingArray.add("test");
        testingArray.add("A");
        testingArray.add("B");
        testingArray.add("C");
    }


    @Test
    public void get() {

        String result = testingArray.get(0);
        assertThat(result, is("test"));
    }

    @Test
    public void set() {
        testingArray.set(1, "K");
        String result = testingArray.get(1);
        assertThat(result, is("K"));
    }


    @Test
    public void removeArrayElement() {
        testingArray.remove(1);
        String result = testingArray.get(1);
        assertThat(result, is("B"));
    }

    @Test
    public void hasNext() {
        SimpleArray<Integer> iteratorTest = new SimpleArray<>(10);
        iteratorTest.add(1);
        iteratorTest.hasNext();
        assertThat(iteratorTest.hasNext(), is(true));
        iteratorTest.next();
        iteratorTest.hasNext();
        assertThat(iteratorTest.hasNext(), is(false));

    }


    @Test
    public void removeWithIterator() {
        SimpleArray<Integer> iteratorTest = new SimpleArray<>(10);
        iteratorTest.add(1);
        iteratorTest.add(2);
        iteratorTest.add(3);
        iteratorTest.next();
        iteratorTest.remove();
        var result = iteratorTest.next();
        assertThat(result, is(3));
    }


}
