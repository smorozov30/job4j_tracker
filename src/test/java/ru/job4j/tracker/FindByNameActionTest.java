package ru.job4j.tracker;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class FindByNameActionTest {
    @Test
    public void whenCheckFindByName() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream def = System.out;
        System.setOut(new PrintStream(out));
        ITracker tracker = new Tracker();
        Item item = new Item("fix bug");
        tracker.add(item);
        FindByNameAction fbna = new FindByNameAction("= Find items by name ==");
        fbna.execute(new StubInput(new String[] {item.getName()}), tracker, System.out::println);
        String expect = new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("1 items found:")
                .add(item.getName() + ": id(" + item.getId() + ")")
                .toString();
        assertThat(new String(out.toByteArray()), is(expect));
        System.setOut(def);
    }
}
