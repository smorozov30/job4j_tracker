package ru.job4j.tracker.action;

import ru.job4j.tracker.store.Tracker;
import ru.job4j.tracker.io.input.Input;
import ru.job4j.tracker.model.Item;

import java.util.List;
import java.util.function.Consumer;

/**
 * Класс выводит все заявки.
 *
 * @author Sergey Morozov (morozov.java.job@gmail.com).
 * @version 1.0
 */
public class ShowAllAction extends BaseAction {

    /**
     * Конструктор класса.
     * @param name - тмя для меню.
     */
    public ShowAllAction(String name) {
        super(name);
    }

    /**
     * Выводит все заявки.
     * @param input объект класса отвечающий за ввод данных.
     * @param tracker основной класс для работы с заявками.
     * @return результат выполнения добавления.
     */
    @Override
    public boolean execute(Input input, Tracker tracker, Consumer<String> output) {
        boolean result = false;
        List<Item> items = tracker.findAll();
        if (items.size() > 0) {
            output.accept(items.size() + " items found:");
            for (Item item : items) {
                output.accept(item.getName());
            }
            result = true;
        } else {
            System.out.println("No items found");
        }
        return result;
    }
}
