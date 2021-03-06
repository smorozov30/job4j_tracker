package ru.job4j.newtracker.action;

import ru.job4j.newtracker.io.input.Input;
import ru.job4j.newtracker.model.Item;
import ru.job4j.newtracker.store.Tracker;

import java.util.function.Consumer;

/**
 * Класс заменяет заявки в системе.
 *
 * @author Sergey Morozov (morozov.java.job@gmail.com).
 * @version 1.0
 */
public class ReplaceAction extends BaseAction {

    /**
     * Конструктор класса ReplaceAction.
     * @param name - имя для меню.
     */
    public ReplaceAction(String name) {
        super(name);
    }

    /**
     * Заменяет заявку по id.
     * @param input объект класса отвечающий за ввод данных.
     * @param tracker основной класс для работы с заявками.
     * @return результат выполнения добавления.
     */
    @Override
    public boolean execute(Input input, Tracker tracker, Consumer<String> output) {
        boolean result = false;
        Item previous = tracker.findById(input.askStr("Enter id: "));
        output.accept("");
        if (previous != null) {
            Item next = Item.of(input.askStr("Enter a name to replace: "));
            next.setId(previous.getId());
            tracker.replace(previous.getId(), next);
            result = true;
            output.accept("");
            output.accept("Item replaced");
        } else {
            output.accept("Item not found");
        }
        return result;
    }
}
