package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.action.FindByNameAction;
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
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Тест на класс FindByNameAction.
 *
 * @author Sergey Morozov (morozov.java.job@gmail.com)
 * @version 1.0
 */
public class FindByNameActionTest {

    @Test
    public void testFindByNameReturnTrue() {
        UserAction action = new FindByNameAction("findByName action");
        Tracker tracker = new MemTracker();
        Output output = new StubOutput();
        Item item = new Item("Name item");
        tracker.add(item);
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn(item.getName());
        List<String> expected = Arrays.asList(
                "1 items found:",
                item.getName() + ": id(" + item.getId() + ")"
        );
        assertTrue(action.execute(input, tracker, output::output));
        assertEquals(output.getOutput(), expected);
    }

    @Test
    public void testFindByNameReturnFalse() {
        UserAction action = new FindByNameAction("findByName action");
        Tracker tracker = new MemTracker();
        Output output = new StubOutput();
        Item item = new Item("Name item");
        tracker.add(item);
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn("any name");
        List<String> expected = Arrays.asList("Items not found");
        assertFalse(action.execute(input, tracker, output::output));
        assertEquals(output.getOutput(), expected);
    }

    /**
     * Тестируем возвращение результата поиска заявки.
     */
    @Test
    public void whenItemNotExistThanReturnFalse() {
        Tracker tracker = new MemTracker();
        Input input = new StubInput(new String[] {"123456"});
        UserAction action = new FindByNameAction("= Find items by name ==");
        assertFalse(action.execute(input, tracker, System.out::println));
    }

    /**
     * Тестируем вывод оповещения при найденной заявке.
     */
    @Test
    public void whenItemExistThanReturnText() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        Tracker tracker = new MemTracker();
        Item item = new Item("fix bug");
        tracker.add(item);
        Input input = new StubInput(new String[] {item.getName()});
        UserAction action = new FindByNameAction("= Find items by name ==");
        action.execute(input, tracker, System.out::println);
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("1 items found:")
                .add(item.getName() + ": id(" + item.getId() + ")")
                .toString();
        assertEquals(expect, out.toString());
        System.setOut(def);
    }
}
