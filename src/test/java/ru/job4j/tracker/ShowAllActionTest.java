package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.action.ShowAllAction;
import ru.job4j.tracker.action.UserAction;
import ru.job4j.tracker.io.input.StubInput;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.store.Tracker;
import ru.job4j.tracker.store.MemTracker;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Тест на класс ShowAllAction.
 *
 * @author Sergey Morozov (morozov.java.job@gmail.com)
 * @version 1.0
 */
public class ShowAllActionTest {

    /**
     * Тестируем вывод всех заявок.
     */
    @Test
    public void whenCheckOutput() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        Tracker tracker = new MemTracker();
        Item item = new Item("fix bug");
        tracker.add(item);
        ShowAllAction act = new ShowAllAction("====== All Items ======");
        act.execute(new StubInput(new String[] {}), tracker, System.out::println);
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("1 items found:")
                .add(item.getName())
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
        System.setOut(def);
    }

    /**
     * Тестируем отсутствие заявок.
     */
    @Test
    public void whenNotExistItems() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        Tracker tracker = new MemTracker();
        UserAction action = new ShowAllAction("====== All Items ======");
        action.execute(new StubInput(new String[] {}), tracker, System.out::println);
        String expect = "No items found" + System.lineSeparator();
        assertThat(new String(out.toByteArray()), is(expect));
        System.setOut(def);
    }
}
