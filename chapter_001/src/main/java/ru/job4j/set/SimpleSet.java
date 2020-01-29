package ru.job4j.set;

import ru.job4j.list.DynamicContainer;

import java.util.Iterator;

public class SimpleSet<E> implements Iterable<E> {

    private DynamicContainer<E> container;

    public SimpleSet() {
        this.container = new DynamicContainer<E>();
    }

    public int size() {
        return container.size();
    }

    public boolean add(E value) {
        var result = !contains(value);
        if (result) {
            container.add(value);
        }
        return result;
    }

    public boolean contains(E value) {
        boolean result = false;
        var it = container.iterator();
        while (it.hasNext()) {
            if (it.next().equals(value)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return container.iterator();
    }
}
