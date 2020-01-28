package ru.job4j.generics;


import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;

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
        Iterator it = iteratorTest.iterator();
        iteratorTest.add(1);
        it.hasNext();
        assertThat(iteratorTest.iterator().hasNext(), is(true));
        it.next();
        it.hasNext();
        assertThat(it.hasNext(), is(false));

    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testExceptionWhenAddedBiggerThanInitSize() {
        SimpleArray<Integer> iteratorTest = new SimpleArray<>(1);
        iteratorTest.add(1);
        iteratorTest.add(2);
    }


    @Test(expected = IndexOutOfBoundsException.class)
    public void testExceptionWhenGetMoreThanSize() {
        SimpleArray<Integer> iteratorTest = new SimpleArray<>(1);
        iteratorTest.add(1);
        iteratorTest.get(1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testExceptionWhenSetInexMoreThanSize() {
        SimpleArray<Integer> iteratorTest = new SimpleArray<>(1);
        iteratorTest.add(1);
        iteratorTest.set(1, 1);
    }

}
