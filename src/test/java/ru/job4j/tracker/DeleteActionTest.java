package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.action.DeleteAction;
import ru.job4j.tracker.action.UserAction;
import ru.job4j.tracker.io.input.Input;
import ru.job4j.tracker.io.input.StubInput;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.io.output.Output;
import ru.job4j.tracker.io.output.StubOutput;
import ru.job4j.tracker.store.ITracker;
import ru.job4j.tracker.store.Tracker;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Тест на класс DeleteAction.
 *
 * @author Sergey Morozov (morozov.java.job@gmail.com)
 * @version 1.0
 */
public class DeleteActionTest {

    @Test
    public void testDeleteWhenReturnTrue() {
        UserAction action = new DeleteAction("delete action");
        ITracker tracker = new Tracker();
        Output output = new StubOutput();
        Item item = new Item("Deleted item");
        tracker.add(item);
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn(item.getId());
        List<String> expected = Arrays.asList("Item deleted");
        assertTrue(action.execute(input, tracker, output::output));
        assertEquals(output.getOutput(), expected);
    }

    @Test
    public void testDeleteWhenReturnFalse() {
        UserAction action = new DeleteAction("delete action");
        ITracker tracker = new Tracker();
        Output output = new StubOutput();
        Item item = new Item("Deleted item");
        tracker.add(item);
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn("any id");
        List<String> expected = Arrays.asList("Item not found");
        assertFalse(action.execute(input, tracker, output::output));
        assertEquals(output.getOutput(), expected);
    }

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