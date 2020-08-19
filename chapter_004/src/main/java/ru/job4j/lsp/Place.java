package ru.job4j.lsp;

import java.util.ArrayList;
import java.util.List;

public abstract class Place implements PlaceCriteria {
    private List<Food> food;

    public Place() {
        food = new ArrayList<Food>();
    }

    public List<Food> getFood() {
        return food;
    }

    public boolean addFood(Food f) {
        return food.add(f);
    }

    public boolean deleteFood(Food f) {
        return food.remove(f);
    }

}
