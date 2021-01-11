package ru.job4j.newtracker;

import ru.job4j.newtracker.action.*;
import ru.job4j.newtracker.io.input.ConsoleInput;
import ru.job4j.newtracker.io.input.Input;
import ru.job4j.newtracker.io.input.ValidateInput;
import ru.job4j.newtracker.store.HibernateTracker;
import ru.job4j.newtracker.store.Tracker;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Класс представляет консольное приложение для работы с классом Tracker.
 *
 * @author Sergey Morozov (morozov.java.job@gmail.com).
 * @version 1.0
 */
public class StartUI {

    /**
     * Переменная для вывода данных с помощью Consumer.
     */
    private final Consumer<String> output;

    /**
     *  Конструктор класса StartUI.
     * @param output
     */
    public StartUI(Consumer<String> output) {
        this.output = output;
    }

    /**
     * Выводит на экран меню, принимает ввод пользователя, выполняет выбраное действие.
     * @param input объект класса, используемый для ввода данных с консоли.
     * @param tracker объект класса хранения заявок, используется для работы с заявками.
     */
    public void init(Input input, Tracker tracker, List<UserAction> actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions, output);
            int select = input.askInt("Select: ", actions.size());
            UserAction action = actions.get(select);
            run = action.execute(input, tracker, output);
        }
    }

    /**
     * Выводит в консоль пункты меню приложения.
     */
    private void showMenu(List<UserAction> actions, Consumer<String> output) {
        System.out.println("========== Menu ==========");
        for (int index = 0; index < actions.size(); index++) {
            output.accept(index + ". " + actions.get(index).name());
        }
        output.accept(actions.size() + ". ======== Exit =========");
    }

    /**
     * Старт работы приложения.
     * @param args - аргументы командной строки.
     */
    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Input validate = new ValidateInput(input);
        Tracker tracker = HibernateTracker.instOf();
        List<UserAction> actions = Arrays.asList(
                                new CreateAction("= Create a new Item ==="),
                                new ShowAllAction("====== All Items ======"),
                                new ReplaceAction("===== Edit item ======="),
                                new DeleteAction("===== Delete item ====="),
                                new FindByIdAction("=== Find item by Id ==="),
                                new FindByNameAction("= Find items by name ==")
                                );
        new StartUI(System.out::println).init(validate, tracker, actions);
    }
}
