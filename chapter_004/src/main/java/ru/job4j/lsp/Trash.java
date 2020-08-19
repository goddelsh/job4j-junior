package ru.job4j.lsp;

public class Trash extends Place {

    final private double ACCEPTING_PERCENT = 1;

    @Override
    public boolean checkFood(Food f) {
        return f.getPercent() >= ACCEPTING_PERCENT;
    }
}
