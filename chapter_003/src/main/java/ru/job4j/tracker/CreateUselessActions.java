package ru.job4j.tracker;

import java.util.ArrayList;

public class CreateUselessActions extends BaseAction{

    static private ArrayList<Item> uselesItems;

    public CreateUselessActions(int key, String name) {
        super(key, name);
        uselesItems = new ArrayList<>();
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        for (int i = 0; i < 10000; i++) {
            uselesItems.add(new Item(" " + uselesItems.size()));
        }
        System.out.println(uselesItems.size());
        return true;
    }
}
