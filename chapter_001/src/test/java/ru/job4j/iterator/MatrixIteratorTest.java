package ru.job4j.iterator;

import org.junit.Before;
import org.junit.Test;


import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class MatrixIteratorTest {

    private Iterator it;

    @Before
    public void setUp() {
        it = new MatrixIterator(new int[][]{{1}, {3, 4}, {7}});
    }

    @Test
    public void makeArrayFlat() {
        var mit = (MatrixIterator) it;
        var result = mit.makeArrayFlat(new int[][]{{1}, {3, 4}, {7}});
        var expected = new int[]{1, 3, 4, 7};
        assertThat(result, is(expected));
    }

    @Test
    public void hasNext() {
    }

    @Test
    public void next() {
    }

    @Test
    public void testsThatNextMethodDoesntDependsOnPriorHasNextInvocation() {
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(7));
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(3));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(7));
    }

    @Test
    public void hasNextNextSequentialInvocation() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(7));
        assertThat(it.hasNext(), is(false));
    }
}
