package ru.job4j.tracker;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Тест на класс ReplaceAction.
 *
 * @author Sergey Morozov (morozov.java.job@gmail.com)
 * @version 1.0
 */
public class ReplaceActionTest {

    /**
     * Тестируем положительный случай замены заявки в системе.
     */
    @Test
    public void whenItemReplacedThanReturnTrue() {
        ITracker tracker = new Tracker();
        Item item = new Item("item");
        tracker.add(item);
        Input input = new StubInput(new String[] {item.getId(), "new item"});
        UserAction action = new ReplaceAction("replace action");
        assertTrue(action.execute(input, tracker, System.out::println));
    }

    /**
     * Тестируем положительный случай замены заявки в системе.
     */
    @Test
    public void whenItemNotReplacedThanReturnFalse() {
        ITracker tracker = new Tracker();
        Input input = new StubInput(new String[] {"123456"});
        UserAction action = new ReplaceAction("replace action");
        assertFalse(action.execute(input, tracker, System.out::println));
    }
}