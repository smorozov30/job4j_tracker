package ru.job4j.newtracker.action;

import ru.job4j.newtracker.io.input.Input;
import ru.job4j.newtracker.store.Tracker;

import java.util.function.Consumer;

/**
 * Класс удаляет заявку из системы.
 *
 * @author Sergey Morozov (morozov.java.job@gmail.com).
 * @version 1.0
 */
public class DeleteAction extends BaseAction {

    /**
     * Конструктор класса DeleteAction.
     * @param name - имя действия для меню.
     */
    public DeleteAction(String name) {
        super(name);
    }

    /**
     * Удаляет из системы заявку по id.
     * @param input объект класса отвечающий за ввод данных.
     * @param tracker основной класс для работы с заявками.
     * @return результат выполнения добавления.
     */
    @Override
    public boolean execute(Input input, Tracker tracker, Consumer<String> output) {
        String id = input.askStr("Enter id: ");
        boolean result = tracker.delete(id);
        output.accept(result ? "Item deleted" : "Item not found");
        return result;
    }
}
