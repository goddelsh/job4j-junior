package ru.job4j.tracker;

import java.util.function.Consumer;

public class ValidateInput implements Input {
    private final Input input;
    private Consumer<String> output;

    public ValidateInput(Input input, Consumer<String> output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public String askStr(String question) {
        return this.input.askStr(question);
    }

    @Override
    public int askInt(String question) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = this.input.askInt(question);
                invalid = false;
            } catch (NumberFormatException nfe) {
                output.accept("Please enter validate data again.");
            }
        } while (invalid);
        return value;
    }

    @Override
    public int askInt(String question, int max) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = this.input.askInt(question, max);
                invalid = false;
            } catch (IllegalStateException moe) {
                output.accept("Please select key from menu.");
            } catch (NumberFormatException nfe) {
                output.accept("Please enter validate data again.");
            }
        } while (invalid);
        return value;
    }

}
