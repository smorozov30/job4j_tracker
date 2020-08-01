package ru.job4j.tracker;

import java.util.function.Consumer;

/**
 * Класс осуществляет действие поиска заявки по id.
 * @author smorozov30 (sergey.se1ove.morozov@gmail.com).
 */
public class FindByIdAction extends BaseAction {

    protected FindByIdAction(String name) {
        super(name);
    }

    /**
     * Метод ищет заявку по id используя метод из класса Tracker.
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
