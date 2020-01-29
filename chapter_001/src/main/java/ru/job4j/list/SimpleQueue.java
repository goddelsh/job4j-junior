package ru.job4j.list;

public class SimpleQueue<T>  {
    private SimpleStack<T> left;
    private SimpleStack<T> rigtt;


    public SimpleQueue() {
        this.left = new SimpleStack<T>();
        this.rigtt = new SimpleStack<T>();
    }


    public T poll() {
        T result = null;
        if (!left.isEmpty()) {
            result = reduse(left.poll());
        }
        return result;
    }

    private T reduse(T value) {
        T result;
        if (!left.isEmpty()) {
            result = reduse(left.poll());
            left.push(value);
        } else {
            result = value;
        }
        return result;
    }


    public void push(T value) {
        left.push(value);
    }
}
