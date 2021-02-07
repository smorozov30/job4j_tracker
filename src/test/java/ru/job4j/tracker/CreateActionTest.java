package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.action.CreateAction;
import ru.job4j.tracker.action.UserAction;
import ru.job4j.tracker.io.input.Input;
import ru.job4j.tracker.io.input.StubInput;
import ru.job4j.tracker.io.output.Output;
import ru.job4j.tracker.io.output.StubOutput;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.store.Tracker;
import ru.job4j.tracker.store.MemTracker;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Тест на класс CreateAction.
 *
 * @author Sergey Morozov (morozov.java.job@gmail.com)
 * @version 1.0
 */
public class CreateActionTest {

    @Test
    public void testCreate() {
        UserAction action = new CreateAction("create action");
        Tracker tracker = new MemTracker();
        Output output = new StubOutput();
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn("New item");
        List<String> expected = Arrays.asList("Item added");
        assertTrue(action.execute(input, tracker, output::output));
        List<Item> items = new ArrayList<>();
        tracker.findAll(items::add);
        assertThat(items.get(0).getName(), is("New item"));
        assertEquals(output.getOutput(), expected);
    }

    /**
     * Тестируем добавление заявки в систему.
     */
    @Test
    public void whenAddNewItemThanReturnTrue() {
        Input input = new StubInput(new String[] {"hello"});
        Tracker tracker = new MemTracker();
        UserAction userAction = new CreateAction("create item");
        assertTrue(userAction.execute(input, tracker, System.out::println));
    }

    /**
     * Тестируем вывод текстового сообщения в консоль при добавлении новой заявки.
     */
    @Test
    public void whenAddNewItemThanOutputOnConsole() {
        Input input = new StubInput(new String[] {"hello"});
        Tracker tracker = new MemTracker();
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