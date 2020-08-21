package ru.job4j.isp;

public interface Menu {
    void addItem(String name, String afterName, ItemLogic action);
    void printMenu();
    Item findByName(String name);
}
