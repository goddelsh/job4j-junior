package ru.job4j.tracker;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TrackerTest {
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        MemTracker tracker = new MemTracker();
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItemByName() {
        MemTracker tracker = new MemTracker();
        Item item = new Item("test1");
        tracker.add(item);
        ArrayList<Item> result = tracker.findByName(item.getName());
        assertEquals(1, result.size());
        assertThat(result.get(0).getName(), is(item.getName()));
    }

    @Test
    public void whenAddNewItemsThenDeleteMiddleAndLast() {
        MemTracker tracker = new MemTracker();
        Item item = new Item("test1");
        tracker.add(item);
        Item item2 = new Item("test2");
        tracker.add(item2);
        Item item3 = new Item("test3");
        tracker.add(item3);
        Item item4 = new Item("test4");
        tracker.add(item4);
        tracker.delete(item2.getId());
        tracker.delete(item4.getId());
        ArrayList<Item> result = tracker.findAll();
        assertEquals(2, result.size());
        assertThat(result.get(0).getId(), is(item.getId()));
        assertThat(result.get(1).getId(), is(item3.getId()));
    }


    @Test
    public void whenReplaceNameThenReturnNewName() {
        MemTracker tracker = new MemTracker();
        Item previous = new Item("test1");
        // Добавляем заявку в трекер. Теперь в объект проинициализирован id.
        tracker.add(previous);
        // Создаем новую заявку.
        Item next = new Item("test2");
        // Проставляем старый id из previous, который был сгенерирован выше.
        next.setId(previous.getId());
        // Обновляем заявку в трекере.
        tracker.replace(previous.getId(), next);
        // Проверяем, что заявка с таким id имеет новые имя test2.
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }
}