package ru.job4j.tracker;

import org.w3c.dom.css.Counter;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

public class PrintAllAction extends BaseAction  {
    public PrintAllAction(int key, String name) {
        super(key, name);
    }

    private class Counter implements Supplier<Integer> {
        public Integer getCount() {
            return count;
        }

        private Integer count = 0;

        @Override
        public Integer get() {
            return count++;
        }


    }
    @Override
    public boolean execute(Input input, Store tracker) {
        Counter counter = new Counter();
        tracker.findAll(item -> System.out.println(counter.get() + ". id: " + item.getId() + " name: " + item.getName()));

        if (counter.getCount()  == 0) {
            System.out.println("Items list is empty");
        }
        return true;
    }
}
