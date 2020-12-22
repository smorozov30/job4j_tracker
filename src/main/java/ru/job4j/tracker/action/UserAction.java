package ru.job4j.tracker.action;

import ru.job4j.tracker.store.ITracker;
import ru.job4j.tracker.io.input.Input;

import java.util.function.Consumer;

/**
 * Интерфейс для классов реализующих действия меню.
 *
 * @author Sergey Morozov (morozov.java.job@gmail.com)
 * @version 1.0
 */
public interface UserAction {

    /**
     * Возвращает название пункта меню.
     * @return - строка название.
     */
    String name();

    /**
     * Выполняет заданное действие меню.
     * @param input - поток ввода данных в систему.
     * @param tracker - хранилище системы.
     * @param output - вывод данных из системы.
     * @return - результат выполнения действия.
     */
    boolean execute(Input input, ITracker tracker, Consumer<String> output);
}
