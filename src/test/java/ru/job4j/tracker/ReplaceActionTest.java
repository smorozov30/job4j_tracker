package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.action.ReplaceAction;
import ru.job4j.tracker.action.UserAction;
import ru.job4j.tracker.io.input.Input;
import ru.job4j.tracker.io.input.StubInput;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.io.output.Output;
import ru.job4j.tracker.io.output.StubOutput;
import ru.job4j.tracker.store.Tracker;
import ru.job4j.tracker.store.MemTracker;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Тест на класс ReplaceAction.
 *
 * @author Sergey Morozov (morozov.java.job@gmail.com)
 * @version 1.0
 */
public class ReplaceActionTest {

    @Test
    public void testReplaceWhenTrue() {
        UserAction action = new ReplaceAction("replace action");
        Tracker tracker = new MemTracker();
        Item item = new Item("Replaced item");
        Output output = new StubOutput();
        Input input = mock(Input.class);
        tracker.add(item);
        when(input.askStr("Enter id: ")).thenReturn(item.getId());
        when(input.askStr("Enter a name to replace: ")).thenReturn("New item name");
        List<String> expected = Arrays.asList("", "", "Item replaced");
        assertTrue(action.execute(input, tracker, output::output));
        assertThat(tracker.findAll().get(0).getName(), is("New item name"));
        assertEquals(output.getOutput(), expected);
    }

    @Test
    public void testReplaceWhenFalse() {
        UserAction action = new ReplaceAction("replace action");
        Tracker tracker = new MemTracker();
        Item item = new Item("Replaced item");
        Output output = new StubOutput();
        Input input = mock(Input.class);
        tracker.add(item);
        when(input.askStr("Enter id: ")).thenReturn("any id");
        when(input.askStr("Enter a name to replace: ")).thenReturn("New item name");
        List<String> expected = Arrays.asList("", "Item not found");
        assertFalse(action.execute(input, tracker, output::output));
        assertThat(tracker.findAll().get(0).getName(), is("Replaced item"));
        assertEquals(output.getOutput(), expected);
    }

    /**
     * Тестируем положительный случай замены заявки в системе.
     */
    @Test
    public void whenItemReplacedThanReturnTrue() {
        Tracker tracker = new MemTracker();
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
        Tracker tracker = new MemTracker();
        Input input = new StubInput(new String[] {"123456"});
        UserAction action = new ReplaceAction("replace action");
        assertFalse(action.execute(input, tracker, System.out::println));
    }
}