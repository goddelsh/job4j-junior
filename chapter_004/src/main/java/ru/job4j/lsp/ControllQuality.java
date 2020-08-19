package ru.job4j.lsp;

import java.util.List;

public class ControllQuality {
    final private List<Place> places;

    public ControllQuality(List<Place> places) {
        this.places = places;
    }

    public void placeFood(List<Food> foodList) {
        for (var food : foodList) {
            for (var place : places) {
                if (place.checkFood(food)) {
                    place.addFood(food);
                    break;
                }
            }
        }
    }
}
