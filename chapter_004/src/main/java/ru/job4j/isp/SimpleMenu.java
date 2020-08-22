package ru.job4j.isp;

import java.util.*;
import java.util.stream.IntStream;

import java.util.stream.Collectors;

public class SimpleMenu implements Menu {

    private Item item;

    @Override
    public void printMenu() {
        printItem(item, 0);
    }

    private void printItem(Item item, int line) {
        String lines =  IntStream.range(0, line).mapToObj(i -> "---").collect(Collectors.joining(""));
        System.out.println(lines + item.getName());
        for (var child: item.getChildren()) {
            printItem(child, line + 1);
        }
    }


    @Override
    public void addItem(String name, String afterName, ItemLogic action) {
        var newItem = new SimpleItem(name);
        newItem.setAction(action);

        if (item == null) {
            item = newItem;
        } else if (afterName != null) {
            var resultitem = findByName(afterName);
            if (resultitem == null) {
                throw new IllegalStateException();
            }
            resultitem.addChild(newItem);
        }
    }


    @Override
    public Item findByName(String name) {
        Item result = null;
        Queue<Item> queue = new LinkedList<>(List.of(item));
        while (queue.size() > 0) {
            var searchObject = queue.poll();
            if (searchObject.getName().contains(name)) {
                result = searchObject;
                break;
            } else {
                for (var obj : searchObject.getChildren()) {
                    queue.offer(obj);
                }
            }
        }
        return result;
    }
}
