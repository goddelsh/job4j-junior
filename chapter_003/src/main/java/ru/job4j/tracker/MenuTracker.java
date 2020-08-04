package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MenuTracker {
    private Input input;
    private List<UserAction> actions = new ArrayList<UserAction>();
    private Store tracker;
    private Consumer<String> output;

    public MenuTracker(Input input, Store tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }


    public void init() {
        actions.add(new CreateAction(0, "create item"));
        actions.add(new PrintAllAction(1, "print all items"));
        actions.add(new EditAction(2, "edit item"));
        actions.add(new DeleteAction(3, "delete item"));
        actions.add(new FindByIdAction(4, "find item by id"));
        actions.add(new FindByNameAction(5, "find item by name"));
        actions.add(new ExitAction(6, "exit"));
    }

    public void setActions(List<UserAction> actions) {
        this.actions = actions;
    }

    public void showMenu() {
        output.accept("Menu.");
        for (UserAction action : actions) {
            output.accept(action.name());
        }
    }

    public boolean selectAction() {
        int select = input.askInt("Select: ", actions.size());
        UserAction action = actions.get(select);
        return startExecute(action);
    }

    public boolean startExecute(UserAction action) {
        return action.execute(input, tracker);
    }

}
