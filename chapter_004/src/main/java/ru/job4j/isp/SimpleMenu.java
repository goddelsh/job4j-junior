package ru.job4j.isp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import  ru.job4j.tree.Tree;

public class SimpleMenu implements Menu {

    List<Tree<Item>> items;

    public SimpleMenu() {
        this.items = new ArrayList<>();
    }

    @Override
    public void printMenu() {
        for (var rootItem : items) {
            Iterator<Item> it = rootItem.iterator();
            while (it.hasNext()) {
                System.out.println(it.next().getName());
            }
        }
    }

    @Override
    public void addItem(String name, String afterName, ItemLogic action) {
        if (findByName(name) != null) {
            throw  new IllegalStateException("Ошибка уникальности имён");
        }

        Tree<Item> itemTree = afterName != null && !afterName.isEmpty() ? findTree(afterName) : null;

        var newItem = new SimpleItem(name);
        newItem.setAction(action);
        if (itemTree != null) {
            itemTree.add(findByName(afterName), newItem);
        } else {
            var newTree = new Tree<Item>(newItem);
            items.add(newTree);
        }
    }

    public Tree<Item> findTree(String name) {
        Tree<Item> result = null;
        var searchObject = new SimpleItem(name);
        for (var rootItem : items) {
            var searchResult = rootItem.findBy(searchObject);
            if (searchResult.isPresent()) {
                result = rootItem;
                break;
            }
        }
        return result;
    }

    @Override
    public Item findByName(String name) {
        Item result = null;
        var searchObject = new SimpleItem(name);
        for (var rootItem : items) {
            var searchResult = rootItem.findBy(searchObject);
            if (searchResult.isPresent()) {
                result = searchResult.get().getValue();
            }
        }
        return result;
    }
}
