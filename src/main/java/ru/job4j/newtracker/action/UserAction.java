package ru.job4j.newtracker.action;

import ru.job4j.newtracker.io.input.Input;
import ru.job4j.newtracker.store.Tracker;

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
    boolean execute(Input input, Tracker tracker, Consumer<String> output);
}
