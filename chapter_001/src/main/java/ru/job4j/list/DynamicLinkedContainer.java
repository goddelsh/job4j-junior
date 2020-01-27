package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;

public class DynamicLinkedContainer<E> implements Iterable<E>{

    private int modCount, expectedModCount;
    private int size;
    private Node<E> first;
    private Node<E> last;

    void add(E value) {
        Node<E> newLink = new Node<>(value);

        LinkedList<String> object = new LinkedList<String>();
        this.size++;
        modCount++;
    }

    E get(int index) {
        Node<E> result = this.first;
        for (int i = 0 ; i  < size - 1 ; i++) {
            result = result.next;
        }
        return result.data;
    }

    @Override
    public Iterator<E> iterator() {

        expectedModCount = modCount;

        return new Iterator<E>() {

            private int iteratorIndex = 0;

            private void checkModCount() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
            }

            @Override
            public boolean hasNext() {
                checkModCount();
                return iteratorIndex < size;
            }

            @Override
            public E next() {
                E result;

                checkModCount();

                if (hasNext()) {
                    result = get(iteratorIndex++);
                } else {
                    throw new IndexOutOfBoundsException();
                }
                return result;
            }
        };
    }

    private static class Node<E> {

        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }
}
