package ru.job4j.iterator;


import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;

public class IteratorForEvenTest {

    @Test
    public void getOnlyEvenElements() {
        var inputArray = new int[]{1, 2, 3, 4};
        var result = new IteratorForEven(inputArray).getOnlyEvenElements(inputArray);
        var expected = new int[]{2, 4};
        assertThat(result, is(expected));
    }

    @Test
    public void hasNext() {
        var inputArray = new int[]{1, 2, 3, 4};
        var iterator = new IteratorForEven(inputArray);
        iterator.next();
        iterator.hasNext();
        assertThat(iterator.hasNext(), is(true));
        iterator.next();
        iterator.hasNext();
        assertThat(iterator.hasNext(), is(false));
    }

    @Test
    public void next() {
        var inputArray = new int[]{1, 2, 3, 4};
        var iterator = new IteratorForEven(inputArray);
        var firstResult = iterator.next();
        var secondResult = iterator.next();
        assertThat(firstResult, is(2));
        assertThat(secondResult, is(4));
    }
}
