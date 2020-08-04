package ru.job4j.tracker;

public class FindByIdAction extends BaseAction  {


    public FindByIdAction(int key, String name) {
        super(key, name);
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        System.out.println("Enter id: ");
        String id = input.askStr("");
        Item item = tracker.findById(id);
        if (item != null) {
            System.out.println("Founded. id: " + item.getId() + " name: " + item.getName());
        } else {
            System.out.println("Error. Item with this id didn't find");
        }
        return true;
    }
}
