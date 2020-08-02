package ru.job4j.tracker;

import java.util.function.Consumer;

/**
 * Класс ищет заявку с системе по id.
 *
 * @author Sergey Morozov (morozov.java.job@gmail.com).
 * @version 1.0
 */
public class FindByIdAction extends BaseAction {

    /**
     * Конструктор для класса FindByIdAction.
     * @param name - имя для меню.
     */
    protected FindByIdAction(String name) {
        super(name);
    }

    /**
     * Ищет заявку в системе по id.
     * @param input объект класса отвечающий за ввод данных.
     * @param tracker основной класс для работы с заявками.
     * @return результат выполнения добавления.
     */
    @Override
    public boolean execute(Input input, ITracker tracker, Consumer<String> output) {
        boolean result = false;
        Item item = tracker.findById(input.askStr("Enter id: "));
        if (item != null) {
            output.accept("Item found: " + item.getName());
            result = true;
        } else {
            output.accept("Item not found");
        }
        return result;
    }
}
