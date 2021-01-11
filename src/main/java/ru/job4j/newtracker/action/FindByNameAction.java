package ru.job4j.newtracker.action;

import ru.job4j.newtracker.io.input.Input;
import ru.job4j.newtracker.model.Item;
import ru.job4j.newtracker.store.Tracker;

import java.util.List;
import java.util.function.Consumer;

/**
 * Класс ищет в системе заявки по имени.
 *
 * @author Sergey Morozov (morozov.java.job@gmail.com).
 * @version 1.0
 */
public class FindByNameAction extends BaseAction {

    /**
     * Конструктор класса FindByNameAction.
     * @param name - имя для меню.
     */
    public FindByNameAction(String name) {
        super(name);
    }

    /**
     * Ищет в системе заявку по имени.
     * @param input объект класса отвечающий за ввод данных.
     * @param tracker основной класс для работы с заявками.
     * @return результат выполнения добавления.
     */
    @Override
    public boolean execute(Input input, Tracker tracker, Consumer<String> output) {
        boolean result = false;
        List<Item> items = tracker.findByName(input.askStr("Enter a name to search: "));
        if (items.size() > 0) {
            output.accept(items.size() + " items found:");
            for (Item item : items) {
                output.accept(item.getName() + ": id(" + item.getId() + ")");
            }
            result = true;
        } else {
            output.accept("Items not found");
        }
        return result;
    }
}
