package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class PrintAllAction extends BaseAction  {
    public PrintAllAction(int key, String name) {
        super(key, name);
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        final AtomicInteger aInteger = new AtomicInteger(0);
        tracker.findAll(item -> System.out.println(aInteger.incrementAndGet() + ". id: " + item.getId() + " name: " + item.getName()));

        if (aInteger.get() == 0) {
            System.out.println("Items list is empty");
        }
        return true;
    }
}
