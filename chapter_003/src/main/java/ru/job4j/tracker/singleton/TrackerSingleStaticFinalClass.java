package ru.job4j.tracker.singleton;

import ru.job4j.tracker.Item;

public class TrackerSingleStaticFinalClass {
    private Item item;

    private TrackerSingleStaticFinalClass() {
    }

    public static TrackerSingleStaticFinalClass getInstance() {
        return Holder.INSTANCE;
    }

    public Item add(Item model) {
        item = model;
        return item;
    }

    public Item get() {
        return item;
    }

    private static final class Holder {
        private static final TrackerSingleStaticFinalClass INSTANCE = new TrackerSingleStaticFinalClass();
    }

    public static void main(String[] args) {
        TrackerSingleStaticFinalClass tracker = TrackerSingleStaticFinalClass.getInstance();
    }
}
