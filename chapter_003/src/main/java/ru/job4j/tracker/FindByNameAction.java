package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

public class FindByNameAction  extends BaseAction  {
    public FindByNameAction(int key, String name) {
        super(key, name);
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        System.out.println("Enter name: ");
        String name = input.askStr("");
        List<Item> items = tracker.findByName(name);
        if (items.size() > 0) {
            int index = 0;
            for (Item item : items) {
                System.out.println(index++ + ". id: " + item.getId() + " name: " + item.getName());
            }
        } else {
            System.out.println("Error. Items with this name didn't find");
        }
        return true;
    }
}
