package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class DynamicLinkedContainer<E> implements Iterable<E>{

    private int modCount, expectedModCount;
    private int size;
    private Node<E> first;
    private Node<E> last;

    void add(E value) {
       var exLast = last;
       var newNode = new Node<E>(first, last, value);
       last = newNode;
       if (exLast == null) {
           first = newNode;
       } else{
           exLast.next = newNode;
       }
        this.size++;
        modCount++;
    }

    E get(int index) {
        if (index >= size) {
            throw new NoSuchElementException();
        }
        Node<E> result = this.first;
        for (int i = 0 ; i  < index ; i++) {
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
                checkModCount();

                if (!hasNext()) {
                    throw new IndexOutOfBoundsException();
                }
                return get(iteratorIndex++);
            }
        };
    }

    private static class Node<E> {

        E data;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> next, Node<E> prev, E data) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }
}
