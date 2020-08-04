package ru.job4j.tracker.singleton;

import ru.job4j.tracker.Item;

public enum TrackerSingleEnum {
    INSTANCE; // здесь мы указываем перечисления.

    private Item item;

    // Конструкторы и методы.
    public Item add(Item model) {
        item = model;
        return item;
    }

    public Item get() {
        return item;
    }
}
