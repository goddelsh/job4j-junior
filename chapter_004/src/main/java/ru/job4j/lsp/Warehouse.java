package ru.job4j.lsp;

public class Warehouse extends Place {

    final private double acceptingPercent = 0.25;

    @Override
    public boolean checkFood(Food f) {
        return f.getPercent() < acceptingPercent;
    }
}
