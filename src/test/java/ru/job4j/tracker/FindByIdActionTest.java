package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.action.FindByIdAction;
import ru.job4j.tracker.action.UserAction;
import ru.job4j.tracker.io.input.Input;
import ru.job4j.tracker.io.input.StubInput;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.io.output.Output;
import ru.job4j.tracker.io.output.StubOutput;
import ru.job4j.tracker.store.Tracker;
import ru.job4j.tracker.store.MemTracker;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Тест на класс FindByIdAction.
 *
 * @author Sergey Morozov (morozov.java.job@gmail.com)
 * @version 1.0
 */
public class FindByIdActionTest {

    @Test
    public void testFindByIdReturnTrue() {
        UserAction action = new FindByIdAction("findById action");
        Tracker tracker = new MemTracker();
        Output output = new StubOutput();
        Item item = new Item("Any item");
        tracker.add(item);
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn(item.getId());
        List<String> expected = Arrays.asList("Item found: " + item.getName());
        assertTrue(action.execute(input, tracker, output::output));
        assertEquals(output.getOutput(), expected);
    }

    @Test
    public void testFindByIdReturnFalse() {
        UserAction action = new FindByIdAction("findById action");
        Tracker tracker = new MemTracker();
        Output output = new StubOutput();
        Item item = new Item("Any item");
        tracker.add(item);
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn("any id");
        List<String> expected = Arrays.asList("Item not found");
        assertFalse(action.execute(input, tracker, output::output));
        assertEquals(output.getOutput(), expected);
    }

    /**
     * Тестируем случай когда заявка найдена.
     */
    @Test
    public void whenItemExistThanReturnTrue() {
        Tracker tracker = new MemTracker();
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
        Tracker tracker = new MemTracker();
        Input input = new StubInput(new String[] {"123456"});
        UserAction action = new FindByIdAction("find by id");
        assertFalse(action.execute(input, tracker, System.out::println));
    }

    /**
     * Тестируем вывод текстового сообщения в консоль при поиске заявки по id.
     */
    @Test
    public void whenADeleteItemThanOutputOnConsole() {
        Tracker tracker = new MemTracker();
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