package ru.job4j.tracker;

import org.junit.Test;
import ru.job4j.tracker.action.CreateAction;
import ru.job4j.tracker.action.StubAction;
import ru.job4j.tracker.io.input.StubInput;
import ru.job4j.tracker.store.MemTracker;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Тест на класс StartUI.
 *
 * @author Sergey Morozov (morozov.java.job@gmail.com)
 * @version 1.0
 */
public class StartUITest {

    /**
     * Тестируем выход из системы хранения заявок.
     */
    @Test
    public void whenExit() {
        StubInput input = new StubInput(
                new String[] {"0"}
        );
        StubAction action = new StubAction();
        new StartUI(System.out::println).init(input, new MemTracker(), Arrays.asList(action));
        assertThat(action.isCall(), is(true));
    }

    /**
     * Тестируем вывод меню.
     */
    @Test
    public void whenPrtMenu() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        StubInput input = new StubInput(
                new String[] {"0"}
        );
        StubAction action = new StubAction();
        CreateAction createAction = new CreateAction("==== Create action ====");
        new StartUI(System.out::println).init(input, new MemTracker(), Arrays.asList(action, createAction));
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("========== Menu ==========")
                .add("0. ===== Stub action =====")
                .add("1. ==== Create action ====")
                .add("2. ======== Exit =========")
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
        System.setOut(def);
    }
}
