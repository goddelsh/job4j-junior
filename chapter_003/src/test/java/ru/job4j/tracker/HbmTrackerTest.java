package ru.job4j.tracker;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;


public class HbmTrackerTest {

    Store tracker = new HbmTracker();

    @Test
    public void add() {
        tracker.add(new Item("name1"));
        assertThat(tracker.findById(1).getName(), is("name1"));
    }

    @Test
    public void replace() {
        tracker.add(new Item("name1"));
        assertThat(tracker.findById(1).getName(), is("name1"));
        tracker.replace(1, new Item("name2"));
        assertThat(tracker.findById(1).getName(), is("name2"));
    }

    @Test
    public void delete() {
        tracker.add(new Item("name1"));
        assertThat(tracker.findById(1).getName(), is("name1"));
        tracker.delete(1);
        assertNull(tracker.findById(1));
    }

    @Test
    public void findAll() {
        tracker.add(new Item("name1"));
        tracker.add(new Item("name2"));
        List<Item> all = tracker.findAll();

        assertThat(all.size(), is(2));
        assertThat(all.get(0).getName(), is("name1"));
        assertThat(all.get(1).getName(), is("name2"));
    }

    @Test
    public void findByName() {
        tracker.add(new Item("name1"));
        tracker.add(new Item("name2"));

        List<Item> items = tracker.findByName("name2");
        assertThat(items.size(), is(1));
        assertThat(items.get(0).getId(), is(2));
    }

    @Test
    public void findById() {
        tracker.add(new Item("name1"));
        tracker.add(new Item("name2"));

        Item item = tracker.findById(2);
        assertThat(item.getName(), is("name2"));
    }

}