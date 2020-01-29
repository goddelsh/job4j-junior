package ru.job4j.list;

public class SimpleStack<T> {

    private final DynamicLinkedContainer<T> container;

    public SimpleStack() {
        container = new DynamicLinkedContainer<T>();
    }

    public T poll() {
        return container.removeLast();
    }

    public void push(T value) {
        container.add(value);
    }

    public boolean isEmpty() {
        return container.size() > 0 ? false : true;
    }

}
