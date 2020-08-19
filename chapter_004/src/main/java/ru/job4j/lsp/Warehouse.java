package ru.job4j.lsp;

public class Warehouse extends Place {

    final private double ACCEPTING_PERCENT = 0.25;

    @Override
    public boolean checkFood(Food f) {
        return f.getPercent() < ACCEPTING_PERCENT;
    }
}
