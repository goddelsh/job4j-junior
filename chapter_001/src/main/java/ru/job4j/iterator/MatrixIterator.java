package ru.job4j.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class MatrixIterator implements Iterator {

    private final int[][] innerArray;
    private int index = 0;
    private int jagIndex = 0;

    MatrixIterator(final int[][] inputArray) {
        this.innerArray = inputArray;
    }


    @Override
    public boolean hasNext() {
        var result = false;
        while (index < innerArray.length) {
            if (jagIndex < innerArray[index].length) {
                result = true;
                break;
            } else {
                index++;
                jagIndex = 0;
            }
        }
        return  result;
    }

    @Override
    public Object next() {
        Integer result = null;
        if (hasNext()) {
            result = innerArray[index][jagIndex++];
        } else {
            throw new NoSuchElementException();
        }
        return result;
    }
}
