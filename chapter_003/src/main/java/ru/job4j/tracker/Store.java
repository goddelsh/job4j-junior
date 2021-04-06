package ru.job4j.tracker;


import java.util.List;
import java.util.function.Consumer;

public interface Store extends AutoCloseable {
    void init();
    Item add(Item item);
    boolean replace(int id, Item item);
    boolean delete(int id);
    List<Item> findAll();
    void findAll(Consumer<Item> consumer);
    List<Item> findByName(String key);
    Item findById(int id);
}