package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.action.StubAction;
import ru.job4j.tracker.action.UserAction;
import ru.job4j.tracker.io.input.Input;
import ru.job4j.tracker.io.input.StubInput;
import ru.job4j.tracker.store.ITracker;
import ru.job4j.tracker.store.Tracker;

import static org.junit.Assert.*;

/**
 * Тест на класс StubAction.
 *
 * @author Sergey Morozov (morozov.java.job@gmail.com)
 * @version 1.0
 */
public class StubActionTest {

    /**
     * Тестируем получение имени действия.
     */
    @Test
    public void name() {
        UserAction action = new StubAction();
        String expected = "===== Stub action =====";
        assertEquals(expected, action.name());
    }

    /**
     * Тестируем вхождение в метод для действия.
     */
    @Test
    public void execute() {
        Input input = new StubInput(null);
        ITracker tracker = new Tracker();
        StubAction action = new StubAction();
        assertFalse(action.execute(input, tracker, System.out::println));
        assertTrue(action.isCall());
    }
}