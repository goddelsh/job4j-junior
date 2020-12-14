package ru.job4j.tracker;

import java.util.function.Consumer;

public class StartUI {

    private final Input input;
    private final Store tracker;
    private final Consumer<String> output;

    StartUI(Input input, Store tracker, Consumer<String> output) {
        this.input = input;
        this.tracker = tracker;
        this.output = output;
    }


    void init() {
        Input validate = new ValidateInput(input, this.output);
        MenuTracker menuTracker = new MenuTracker(validate, tracker, output);
        menuTracker.init();
        do {
            menuTracker.showMenu();
        } while (menuTracker.selectAction());

    }

    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Store tracker = new HbmTracker();
        tracker.init();
        new StartUI(input, tracker, System.out::println).init();
    }
}