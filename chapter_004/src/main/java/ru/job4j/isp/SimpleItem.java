package ru.job4j.isp;


public class SimpleItem implements Item {

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
    }


    @Override
    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Item o) {
        return this.name.compareTo(o.getName());
    }

    @Override
    public void doAction() {
        if (action != null) {
            action.activate(this);
        }
    }

}
