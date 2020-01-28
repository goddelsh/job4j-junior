package ru.job4j.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorForEven implements Iterator {

    private final int[] inputArray;
    private int index = 0;


    IteratorForEven(final int[] inputArray) {
        this.inputArray = inputArray;
    }



    public boolean hasNext() {
        var result = false;
        while (index < inputArray.length) {
            if (inputArray[index] % 2 == 0) {
                result = true;
                break;
            } else {
                index++;
            }
        }
        return result;
    }

    public Object next() {
        if (!hasNext()) {
            throw  new NoSuchElementException();
        }
        Integer result = inputArray[index++];
        return result;
    }
}
