package ru.job4j.tracker;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.function.Consumer;

import static org.junit.Assert.*;

/**
 * Тест на класс CreateAction.
 *
 * @author Sergey Morozov (morozov.java.job@gmail.com)
 * @version 1.0
 */
public class CreateActionTest {

    /**
     * Тестируем добавление заявки в систему.
     */
    @Test
    public void whenAddNewItemThanReturnTrue() {
        Input input = new StubInput(new String[] {"hello"});
        ITracker tracker = new Tracker();
        UserAction userAction = new CreateAction("create item");
        assertTrue(userAction.execute(input, tracker, System.out::println));
    }

    /**
     * Тестируем вывод текстового сообщения в консоль при добавлении новой заявки.
     */
    @Test
    public void whenAddNewItemThanOutputOnConsole() {
        Input input = new StubInput(new String[] {"hello"});
        ITracker tracker = new Tracker();
        UserAction userAction = new CreateAction("create item");
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream systemOut = System.out;
        System.setOut(new PrintStream(outputStream));
        userAction.execute(input, tracker, System.out::println);
        String expected = "Item added" + System.lineSeparator();
        assertEquals(expected, outputStream.toString());
        System.setOut(systemOut);
    }
}