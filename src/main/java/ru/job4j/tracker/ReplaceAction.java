package ru.job4j.tracker;

import java.util.function.Consumer;

/**
 * Класс осуществляет действие замены старой заявки - новой.
 * @author smorozov30 (sergey.se1ove.morozov@gmail.com).
 */
public class ReplaceAction extends BaseAction {

    protected ReplaceAction(String name) {
        super(name);
    }

    /**
     * Метод заменяет заявку по id, используя метод из класса Tracker.
     * @param input объект класса отвечающий за ввод данных.
     * @param tracker основной класс для работы с заявками.
     * @return результат выполнения добавления.
     */
    @Override
    public boolean execute(Input input, ITracker tracker, Consumer<String> output) {
        boolean result = false;
        Item previous = tracker.findById(input.askStr("Enter id: "));
        output.accept("");
        if (previous != null) {
            Item next = new Item(input.askStr("Enter a name to replace: "));
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
