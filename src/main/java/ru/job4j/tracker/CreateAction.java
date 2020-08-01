package ru.job4j.tracker;

import java.util.function.Consumer;

/**
 * Класс осуществляет действие добавления новой заявки.
 * @author smorozov30 (sergey.se1ove.morozov@gmail.com).
 */
public class CreateAction extends BaseAction {

    protected CreateAction(String name) {
        super(name);
    }

    /**
     * Метод добавляет новую заявку используя метод из класса Tracker.
     * @param input объект класса отвечающий за ввод данных.
     * @param tracker основной класс для работы с заявками.
     * @return результат выполнения добавления.
     */
    @Override
    public boolean execute(Input input, ITracker tracker, Consumer<String> output) {
        Item item = new Item(input.askStr("Enter name: "));
        tracker.add(item);
        output.accept("Item added");
        return true;
    }
}
