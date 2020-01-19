package ru.job4j.generics;

import java.util.Arrays;
import java.util.Iterator;

public class SimpleArray<E> implements Iterator<E> {

    private Object[] inputArray;
    private int index = 0;
    private int iteratorIndex = 0;


    SimpleArray(int size) {
        this.inputArray = new Object[size];
    }

    int getArraySize() {
        return index;
    }

    void add(E model) {
        this.inputArray[index++] = model;
    }

    void set(int index, E model) {
        this.inputArray[index] = model;
    }

    E get(int index) {
        return (E) this.inputArray[index];
    }

    boolean remove(int index) {
        boolean result = false;
        if (index < this.index) {
            if (this.index - 1 - index >= 0) {
                System.arraycopy(this.inputArray, index + 1, this.inputArray, index, this.index - 1 - index);
            }
            this.index--;
            result = true;
        }
        return result;
    }

    @Override
    public void remove() {
        this.remove(this.iteratorIndex - 1);
    }

    @Override
    public boolean hasNext() {
        return iteratorIndex < index;
    }

    @Override
    public E next() {
        return (E) this.inputArray[this.iteratorIndex++];
    }
}
