package ru.job4j.tracker;

import java.util.function.Consumer;

/**
 * Класс осуществляет действие удаления заявки.
 * @author smorozov30 (sergey.se1ove.morozov@gmail.com).
 */
public class DeleteAction extends BaseAction {

    protected DeleteAction(String name) {
        super(name);
    }

    /**
     * Метод удаляет заявку по id используя метод из класса Tracker.
     * @param input объект класса отвечающий за ввод данных.
     * @param tracker основной класс для работы с заявками.
     * @return результат выполнения добавления.
     */
    @Override
    public boolean execute(Input input, ITracker tracker, Consumer<String> output) {
        String id = input.askStr("Enter id: ");
        boolean result = tracker.delete(id);
        output.accept(result ? "Item deleted" : "Item not found");
        return result;
    }
}
