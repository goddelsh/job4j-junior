package ru.job4j.tracker;

public class EditAction  extends BaseAction {
    protected EditAction(int key, String name) {
        super(key, name);
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        System.out.println("Enter id: ");
        String id = input.askStr("");
        Item item = tracker.findById(id);
        if (item != null) {
            System.out.println("Founded. Enter new name: ");
            String newName = input.askStr("");
            Item newItem = new Item(newName);
            if (tracker.replace(id, newItem)) {
                System.out.println("item edited successfuly");
            } else {
                System.out.println("Editting error");
            }
        } else {
            System.out.println("Error. Item with this id didn't find");
        }
        return true;
    }
}
