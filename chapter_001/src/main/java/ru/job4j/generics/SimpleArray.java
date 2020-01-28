package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<E> implements Iterable<E> {

    private Object[] inputArray;
    private int index = 0;

    SimpleArray(int size) {
        this.inputArray = new Object[size];
    }

    int getArraySize() {
        return index;
    }

    void add(E model) {
        if (!(index < this.inputArray.length)) {
            throw new IndexOutOfBoundsException();
        }
        this.inputArray[index++] = model;
    }

    void set(int index, E model) {
        if ((index < 0) || (index >= this.index)) {
            throw new IndexOutOfBoundsException();
        }
        this.inputArray[index] = model;
    }

    E get(int index) {
        if ((index < 0) || (index >= this.index)) {
            throw new IndexOutOfBoundsException();
        }
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
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int iteratorIndex = 0;

            @Override
            public boolean hasNext() {
                return iteratorIndex < index;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) inputArray[iteratorIndex++];
            }
        };
    }
}
