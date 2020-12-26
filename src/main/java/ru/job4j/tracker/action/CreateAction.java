package ru.job4j.tracker.action;

import ru.job4j.tracker.store.Tracker;
import ru.job4j.tracker.io.input.Input;
import ru.job4j.tracker.model.Item;

import java.util.function.Consumer;

/**
 * Класс добавляет новую заявку с систему.
 *
 * @author Sergey Morozov (morozov.java.job@gmail.com)
 * @version 1.0
 */
public class CreateAction extends BaseAction {

    /**
     * Конструктор класса CreateAction.
     * @param name - название пункта меню, для данного действия.
     */
    public CreateAction(String name) {
        super(name);
    }

    /**
     * Добавляет новую заявку в систему.
     * @param input объект класса отвечающий за ввод данных.
     * @param tracker основной класс для работы с заявками.
     * @return результат выполнения добавления.
     */
    @Override
    public boolean execute(Input input, Tracker tracker, Consumer<String> output) {
        Item item = new Item(input.askStr("Enter name: "));
        tracker.add(item);
        output.accept("Item added");
        return true;
    }
}
