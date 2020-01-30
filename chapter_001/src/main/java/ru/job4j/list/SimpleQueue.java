package ru.job4j.list;

public class SimpleQueue<T>  {
    private SimpleStack<T> left;
    private SimpleStack<T> right;


    public SimpleQueue() {
        this.left = new SimpleStack<T>();
        this.right = new SimpleStack<T>();
    }


    public T poll() {
        if (right.isEmpty()) {
            while (!left.isEmpty()) {
                right.push(left.poll());
            }
        }
        return right.isEmpty() ? null : right.poll();
    }

    public T pollRecurse() {
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
