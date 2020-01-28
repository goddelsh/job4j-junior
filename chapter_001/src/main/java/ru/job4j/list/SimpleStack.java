package ru.job4j.list;

public class SimpleStack<T> {

    private final DynamicLinkedContainerExt<T> container;

    public SimpleStack() {
        container = new DynamicLinkedContainerExt<T>();
    }

    public T poll() {
        return container.get();
    }

    public void push(T value) {
        container.add(value);
    }

    private class DynamicLinkedContainerExt<T> extends DynamicLinkedContainer<T> {
        public T get() {
            T result = this.last.data;
            last = last.prev;
            return result;
        }
    }
}