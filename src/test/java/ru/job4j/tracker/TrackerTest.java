package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * Тест на класс Tracker.
 *
 * @author Sergey Morozov (morozov.java.job@gmail.com)
 * @version 1.0
 */
public class TrackerTest {

    /**
     * Тестируем добавление заявки в систему.
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
     * Тестируем замену заявки в систему с положительным исходом.
     */
    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1");
        tracker.add(previous);
        Item next = new Item("test2");
        next.setId(previous.getId());
        assertTrue(tracker.replace(previous.getId(), next));
    }

    /**
     * Тестируем замену заявки в систему с отрицательным исходом.
     */
    @Test
    public void whenReplaceNameThenReturnFalse() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1");
        tracker.add(previous);
        String notExistId = "1234";
        Item next = new Item("test2");
        next.setId(notExistId);
        assertFalse(tracker.replace(notExistId, next));
    }

    /**
     * Тестируем удаление заявки из системы с положительным исходом.
     */
    @Test
    public void whenDeleteExistItemThenTrue() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        String id = item.getId();
        assertTrue(tracker.delete(id));
    }

    /**
     * Тестируем удаление заявки из системы с отрицательным исходом.
     */
    @Test
    public void whenDeleteNotExistItemThenFalse() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1");
        tracker.add(item);
        String notExistId = "1234";
        assertFalse(tracker.delete(notExistId));
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
