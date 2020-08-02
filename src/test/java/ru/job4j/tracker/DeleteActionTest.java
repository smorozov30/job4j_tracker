package ru.job4j.tracker;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * Тест на класс DeleteAction.
 *
 * @author Sergey Morozov (morozov.java.job@gmail.com)
 * @version 1.0
 */
public class DeleteActionTest {

    /**
     * Тестируем удаление заявки из системы.
     */
    @Test
    public void whenDeleteItemThanReturnTrue() {
        ITracker tracker = new Tracker();
        Item item = new Item("Item");
        tracker.add(item);
        Input input = new StubInput(new String[] {item.getId()});
        UserAction delete = new DeleteAction("Delete Item");
        assertTrue(delete.execute(input, tracker, System.out::println));
    }

    /**
     * Тестируем удаление заявки из системы.
     */
    @Test
    public void whenDeleteItemThanReturnFalse() {
        ITracker tracker = new Tracker();
        Input input = new StubInput(new String[] {"123456"});
        UserAction delete = new DeleteAction("Delete Item");
        assertFalse(delete.execute(input, tracker, System.out::println));
    }

    /**
     * Тестируем вывод текстового сообщения в консоль при удалении заявки.
     */
    @Test
    public void whenADeleteItemThanOutputOnConsole() {
        ITracker tracker = new Tracker();
        Item item = new Item("Item");
        tracker.add(item);
        Input input = new StubInput(new String[] {item.getId()});
        UserAction userAction = new DeleteAction("delete item");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream systemOut = System.out;
        System.setOut(new PrintStream(outputStream));
        userAction.execute(input, tracker, System.out::println);
        String expected = "Item deleted" + System.lineSeparator();
        assertEquals(expected, outputStream.toString());
        System.setOut(systemOut);
    }
}