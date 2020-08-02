package ru.job4j.tracker;

import java.util.function.Consumer;

/**
 * Класс помогает тестировать программу.
 *
 * @author Sergey Morozov (morozov.java.job@gmail.com)
 * @version 1.0
 */
public class StubAction implements UserAction {

    /**
     * Переменная хранит логическое значение вхождения в экземпляры класса.
     */
    private boolean call = false;

    /**
     * Возвращает имя действия.
     * @return - имя.
     */
    @Override
    public String name() {
        return "===== Stub action =====";
    }

    /**
     * Иммитирует действие.
     * @param input
     * @param tracker
     * @param output
     * @return
     */
    @Override
    public boolean execute(Input input, ITracker tracker, Consumer<String> output) {
        call = true;
        return false;
    }

    /**
     * Возвращает результат вхождения тестов в данное действие.
     * @return
     */
    public boolean isCall() {
        return call;
    }
}
