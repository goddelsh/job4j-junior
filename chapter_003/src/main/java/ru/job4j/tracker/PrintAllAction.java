package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

public class PrintAllAction extends BaseAction  {
    public PrintAllAction(int key, String name) {
        super(key, name);
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        List<Item> items = tracker.findAll();
        if (items.size() > 0) {
            int index = 0;
            for (Item item : items) {
                System.out.println(index++ + ". id: " + item.getId() + " name: " + item.getName());
            }
        } else {
            System.out.println("Items list is empty");
        }
        return true;
    }
}
