package ru.job4j.newtracker.store;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.newtracker.model.Item;

import java.util.List;

import static org.junit.Assert.*;

public class HibernateTrackerTest {
    private Tracker tracker;

    @Before
    public void before() {
        tracker = HibernateTracker.instOf();
        List<Item> items = tracker.findAll();
        items.forEach(item -> tracker.delete(String.valueOf(item.getId())));
        tracker = HibernateTracker.instOf();
    }

    @Test
    public void add() {
        Item item = Item.of("name1");
        tracker.add(item);
        List<Item> items = tracker.findAll();
        assertEquals(item, items.get(0));
    }

    @Test
    public void replace() {
        Item item = Item.of("name1");
        item = tracker.add(item);

        Item expected = Item.of("new name");
        expected.setId(item.getId());
        tracker.replace(item.getId(), expected);
        Item result = tracker.findByName("new name").get(0);
        assertEquals(expected, result);
    }

    @Test
    public void delete() {
        Item item = Item.of("name1");
        item = tracker.add(item);

        tracker.delete(String.valueOf(item.getId()));
        List<Item> result = tracker.findAll();
        assertEquals(List.of(), result);
    }

    @Test
    public void findAll() {
        Item item1 = Item.of("name1");
        Item item2 = Item.of("name2");
        tracker.add(item1);
        tracker.add(item2);
        assertEquals(List.of(item1, item2), tracker.findAll());
    }

    @Test
    public void findByName() {
        Item item1 = Item.of("name1");
        Item item2 = Item.of("name2");
        tracker.add(item1);
        tracker.add(item2);

        assertEquals(List.of(item2), tracker.findByName(item2.getName()));
    }

    @Test
    public void findById() {
        Item item1 = Item.of("name1");
        Item item2 = Item.of("name2");
        tracker.add(item1);
        tracker.add(item2);

        assertEquals(item2, tracker.findById(String.valueOf(item2.getId())));
    }
}