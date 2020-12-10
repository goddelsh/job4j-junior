package ru.job4j.tracker;

public class DeleteAction  extends BaseAction  {


    protected DeleteAction(int key, String name) {
        super(key, name);
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        System.out.println("Enter id: ");
        int id = Integer.parseInt(input.askStr(""));
        if (tracker.delete(id)) {
            System.out.println("item edited successfuly");
        } else {
            System.out.println("Deleting error");
        }
        return true;
    }
}
