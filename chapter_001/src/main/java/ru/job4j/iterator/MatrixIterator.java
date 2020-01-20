package ru.job4j.iterator;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class MatrixIterator implements Iterator {

    private final int[] flatArray;
    private int index = 0;

    MatrixIterator(final int[][] inputArray) {
        this.flatArray = makeArrayFlat(inputArray);
    }

    int[] makeArrayFlat(final int[][] inputArray) {
    return Arrays.stream(inputArray)
                .map(iel -> Arrays.stream(iel)
                                .boxed()
                        .collect(Collectors.toList())).flatMap(List::stream)
                .collect(Collectors.toList()).stream().mapToInt(i -> i).toArray();

    }

    @Override
    public boolean hasNext() {
        return index < flatArray.length;
    }

    @Override
    public Object next() {
        return flatArray[index++];
    }
}
