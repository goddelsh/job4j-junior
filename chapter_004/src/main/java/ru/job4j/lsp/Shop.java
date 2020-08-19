package ru.job4j.lsp;

public class Shop extends Place {

    final private double acceptingPercentLeft = 0.25;
    final private double acceptingPercentRight = 0.75;

    @Override
    public boolean checkFood(Food f) {
        var result = false;
        if (f.getPercent() >= acceptingPercentLeft && f.getPercent() < acceptingPercentRight) {
            result = true;
        } else if (f.getPercent() > acceptingPercentRight && f.getPercent() < 1) {
            result = true;
            f.setPrice(f.getPrice() * f.getDisscount());
        }
        return result;
    }
}