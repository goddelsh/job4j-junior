package ru.job4j.isp;

import java.util.List;

public interface Item extends ItemDetails, ItemAction {
   void addChild(Item item);
   List<Item> getChildren();
}
