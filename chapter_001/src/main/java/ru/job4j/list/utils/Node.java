package ru.job4j.list.utils;

public class Node<T> {
    T value;

    public Node(T value) {
        this.value = value;
    }

    public Node<T> next;

    public Node<T> getNext() {
        return next;
    }
}
