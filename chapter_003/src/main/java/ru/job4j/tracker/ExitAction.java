package ru.job4j.tracker;

public class ExitAction extends BaseAction {
    protected ExitAction(int key, String name) {
        super(key, name);
    }


    @Override
    public boolean execute(Input input, Store tracker) {
        return false;
    }
}
