package ru.job4j.lsp;

public class Shop extends Place {

    final private double ACCEPTING_PERCENT_LEFT = 0.25;
    final private double ACCEPTING_PERCENT_RIGHT = 0.75;

    @Override
    public boolean checkFood(Food f) {
        var result = false;
        if (f.getPercent() >= ACCEPTING_PERCENT_LEFT && f.getPercent() < ACCEPTING_PERCENT_RIGHT) {
            result = true;
        } else if (f.getPercent() > ACCEPTING_PERCENT_RIGHT && f.getPercent() < 1) {
            result = true;
            f.setPrice(f.getPrice() * f.getDisscount());
        }
        return result;
    }
}