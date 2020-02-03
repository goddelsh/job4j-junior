package ru.job4j.list;

public class SimpleQueue<T>  {
    private SimpleStack<T> left = new SimpleStack<T>();
    private SimpleStack<T> right = new SimpleStack<T>();


    public T poll() {
        if (right.isEmpty()) {
            while (!left.isEmpty()) {
                right.push(left.poll());
            }
        }
        return right.isEmpty() ? null : right.poll();
    }


    public void push(T value) {
        left.push(value);
    }
}
