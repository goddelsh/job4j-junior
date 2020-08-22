package ru.job4j.isp;


import java.util.ArrayList;
import java.util.List;

public class SimpleItem implements Item {

    private List<Item> children;

    private String name;

    private ItemLogic action;

    public SimpleItem(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAction(ItemLogic action) {
        this.action = action;
        children = new ArrayList<>();
    }


    @Override
    public String getName() {
        return name;
    }


    @Override
    public void doAction() {
        if (action != null) {
            action.activate(this);
        }
    }

    @Override
    public void addChild(Item item) {
        children.add(item);
    }

    @Override
    public List<Item> getChildren() {
        return children;
    }
}
