package ru.job4j.tracker;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Тест на класс FindByNameAction.
 *
 * @author Sergey Morozov (morozov.java.job@gmail.com)
 * @version 1.0
 */
public class FindByNameActionTest {

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
