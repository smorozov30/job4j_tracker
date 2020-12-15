package ru.job4j.tracker;

import org.junit.Test;

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
        ITracker tracker = new Tracker();
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
        ITracker tracker = new Tracker();
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
        ITracker tracker = new Tracker();
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
        ITracker tracker = new Tracker();
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
