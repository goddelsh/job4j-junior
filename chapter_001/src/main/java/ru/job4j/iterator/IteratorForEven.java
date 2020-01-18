package ru.job4j.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorForEven implements Iterator {

    private final int[] inputArray;
    private int index = 0;


    IteratorForEven(final int[] inputArray) {
        this.inputArray = getOnlyEvenElements(inputArray);
    }


    int[] getOnlyEvenElements(final int[] inputArray) {
        return Arrays
                .stream(inputArray)
                //boxing primitive type to equal object
                .boxed()
                .filter(el -> el % 2 == 0)
                .mapToInt(el -> el)
                .toArray();
    }

    public boolean hasNext() {
        return index < inputArray.length;
    }

    public Object next() {
        if (!(index < inputArray.length)) {
            throw  new NoSuchElementException("no more elemnts");
        }
        return inputArray[index++];
    }
}
