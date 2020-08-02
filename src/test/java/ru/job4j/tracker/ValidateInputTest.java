package ru.job4j.tracker;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.is;

/**
 * Тест на класс ValidateInput.
 *
 * @author Sergey Morozov (morozov.java.job@gmail.com)
 * @version 1.0
 */
public class ValidateInputTest {

    /**
     * Тестируем корректность ввода данных пользователем по типу
     * и максимальному значению.
     */
    @Test
    public void whenInvalidInput() {
        ByteArrayOutputStream mem = new ByteArrayOutputStream();
        PrintStream out = System.out;
        System.setOut(new PrintStream(mem));
        ValidateInput input = new ValidateInput(
                new StubInput(new String[] {"invalid", "2", "1"})
        );
        input.askInt("Enter", 1);
        assertThat(
                mem.toString().trim(),
                is("Please enter validate data again."
                + System.lineSeparator()
                + "Please select key from menu")
        );
        System.setOut(out);
    }

    /**
     * Тестируем корректность ввода данных пользователем по типу.
     */
    @Test
    public void whenInvalidInputType() {
        ByteArrayOutputStream mem = new ByteArrayOutputStream();
        PrintStream out = System.out;
        System.setOut(new PrintStream(mem));
        ValidateInput input = new ValidateInput(
                new StubInput(new String[] {"invalid", "1"})
        );
        input.askInt("Enter");
        assertThat(
                mem.toString().trim(),
                is("Please enter validate data again.")
        );
        System.setOut(out);
    }

    /**
     * Тестируем ввод строки пользователем.
     */
    @Test
    public void whenInputTypeString() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[] {"valid string"})
        );
        String expected = "valid string";
        String result = input.askStr("Enter");

        assertThat(result, is(expected));
    }
}
