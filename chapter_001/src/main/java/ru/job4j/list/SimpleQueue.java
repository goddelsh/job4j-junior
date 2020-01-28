package ru.job4j.list;

public class SimpleQueue<T>  {
    private SimpleStack<T> left;
    private SimpleStack<T> rigtt;


    public SimpleQueue() {
        this.left = new SimpleStack<T>();
        this.rigtt = new SimpleStack<T>();
    }

    public T poll() {
        T result;
        while(!left.isEmpty()) {
            rigtt.push(left.poll());
        }
        result = rigtt.poll();
        while(!rigtt.isEmpty()) {
            left.push(rigtt.poll());
        }
        return result;
    }

    public void push(T value) {
        left.push(value);
    }
}
