package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class DynamicContainerTest {

    private DynamicContainer<Integer> dynamicContainer;

    @Before
    public void initContainer() {
        dynamicContainer = new DynamicContainer<>();
        dynamicContainer.add(1);
        dynamicContainer.add(2);
        dynamicContainer.add(3);
    }

    @Test
    public void add() {
        assertThat(dynamicContainer.get(1), is(2));
    }


    @Test
    public void iterator() {
        int index = 1;
        Iterator<Integer> it = dynamicContainer.iterator();
        while (it.hasNext()) {
            assertThat(it.next(), is(index++));
        }
    }

    @Test
    public void checkResizableContainer() {
        DynamicContainer<Integer> dynamicContainer = new  DynamicContainer<Integer>(2);
        for (int i = 0; i < 1000; i++) {
            dynamicContainer.add(i);
        }
        int expected = 0;
        Iterator<Integer> it = dynamicContainer.iterator();
        while (it.hasNext()) {
            assertThat(it.next(), is(expected++));
        }

    }

    @Test(expected = ConcurrentModificationException.class)
    public void modificationControlException() {
        DynamicContainer<Integer> dynamicContainer = new  DynamicContainer<Integer>(2);
        dynamicContainer.add(1);
        Iterator<Integer> it = dynamicContainer.iterator();
        dynamicContainer.add(2);
        it.hasNext();
    }


}