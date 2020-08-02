package ru.job4j.tracker;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Тест на класс FindByIdAction.
 *
 * @author Sergey Morozov (morozov.java.job@gmail.com)
 * @version 1.0
 */
public class FindByIdActionTest {

    /**
     * Тестируем случай когда заявка найдена.
     */
    @Test
    public void whenItemExistThanReturnTrue() {
        ITracker tracker = new Tracker();
        Item item = new Item("Item");
        tracker.add(item);
        Input input = new StubInput(new String[] {item.getId()});
        UserAction action = new FindByIdAction("find by id");
        assertTrue(action.execute(input, tracker, System.out::println));
    }

    /**
     * Тестируем случай когда заявка не найдена.
     */
    @Test
    public void whenItemNotExistThanReturnFalse() {
        ITracker tracker = new Tracker();
        Input input = new StubInput(new String[] {"123456"});
        UserAction action = new FindByIdAction("find by id");
        assertFalse(action.execute(input, tracker, System.out::println));
    }

    /**
     * Тестируем вывод текстового сообщения в консоль при поиске заявки по id.
     */
    @Test
    public void whenADeleteItemThanOutputOnConsole() {
        ITracker tracker = new Tracker();
        Item item = new Item("Item");
        tracker.add(item);
        Input input = new StubInput(new String[] {item.getId()});
        UserAction userAction = new FindByIdAction("find item");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream systemOut = System.out;
        System.setOut(new PrintStream(outputStream));
        userAction.execute(input, tracker, System.out::println);
        String expected = "Item found: " + item.getName() + System.lineSeparator();
        assertEquals(expected, outputStream.toString());
        System.setOut(systemOut);
    }
}