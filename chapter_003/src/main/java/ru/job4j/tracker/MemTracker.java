package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.Random;
import java.util.function.Consumer;


public class MemTracker implements Store {
    /**
     * Массив для хранение заявок.
     */
    private final ArrayList<Item> items = new ArrayList<>();

    int id = 0;

    @Override
    public void init() {

    }

    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(id++);
        this.items.add(item);
        return item;
    }

    private Integer getItemIndexById(int id) {
        Integer result = -1;
        for (int i = 0; i < this.items.size(); i++) {
            if (items.get(i).getId() == id) {
                result = i;
                break;
            }
        }
        return result;
    }

    /**
     * Метод реализаущий замену существующей заявки по id
     * @param item заменяющая заявка
     * @param id заменяймой заявки
     */
    public boolean replace(int id, Item item) {
        boolean result = false;
        Integer index = getItemIndexById(id);
        if (index != -1) {
            this.items.set(index, item);
            result = true;
        }
        return result;
    }

    public boolean delete(int id) {
        boolean result = false;
        int index = getItemIndexById(id);
        if (index != -1) {
            this.items.remove(index);
            result = true;
        }
        return result;
    }

    public ArrayList<Item> findAll() {
        return this.items;
    }

    @Override
    public void findAll(Consumer<Item> consumer) {

    }


    public ArrayList<Item>  findByName(String name) {
        ArrayList<Item> result = new ArrayList<Item>();
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getName().equals(name)) {
                result.add(this.items.get(i));
            }
        }
        return result;
    }

    public Item findById(int id) {
        Item result = null;
        Integer index = getItemIndexById(id);
        if (index != -1) {
            result = this.items.get(index);
        }
        return result;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }

    @Override
    public void close() throws Exception {

    }
}
