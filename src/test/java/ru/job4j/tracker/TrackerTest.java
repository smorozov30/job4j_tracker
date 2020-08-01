package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

public class TrackerTest {

    /**
     * Проверка метода add().
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        Item result = tracker.findById(item.getId());
        assertThat(result.getName(), is(item.getName()));
    }

    /**
     * Проверка метода replace().
     */
    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1");
        tracker.add(previous);
        Item next = new Item("test2");
        next.setId(previous.getId());
        tracker.replace(previous.getId(), next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }

    /**
     * Проверка метода delete().
     */
    @Test
    public void whenDeleteItemThenTrue() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        String id = item.getId();
        boolean result = tracker.delete(id);
        assertThat(result, is(true));
    }

    /**
     * Проверка метода findAll().
     */
    @Test
    public void whenArrayHasThreeItemThenLength3() {
        Tracker tracker = new Tracker();
        Item first = new Item("test1");
        Item second = new Item("test2");
        Item third = new Item("test3");
        tracker.add(first);
        tracker.add(second);
        tracker.add(third);
        int result = tracker.findAll().size();
        int expected = 3;
        assertThat(result, is(expected));
    }

    /**
     * Проверка метода findById().
     */
    @Test
    public void whenSecondIdThanNameTest2() {
        Tracker tracker = new Tracker();
        Item first = new Item("test1");
        Item second = new Item("test2");
        Item third = new Item("test3");
        tracker.add(first);
        Item item = tracker.add(second);
        tracker.add(third);
        String result = tracker.findById(item.getId()).getName();
        String expected = "test2";
        assertThat(result, is(expected));
    }

    /**
     * Проверка метода findByName().
     */
    @Test
    public void whenTwoNamesInArrayThanLength2() {
        Tracker tracker = new Tracker();
        Item first = new Item("test1");
        Item second = new Item("test2");
        Item third = new Item("test3");
        Item fourth = new Item("test2");
        tracker.add(first);
        tracker.add(second);
        tracker.add(third);
        tracker.add(fourth);
        int result = tracker.findByName("test2").size();
        int expected = 2;
        assertThat(result, is(expected));
    }
}
