package ru.job4j.tracker;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Класс представляет консольное приложение для работы с классом Tracker.
 * @author smorozov30 (sergey.se1ove.morozov@gmail.com).
 */
public class StartUI {
    private final Consumer<String> output;

    public StartUI(Consumer<String> output) {
        this.output = output;
    }

    /**
     * В методе выполняется работа с меню.
     * Пользователь вводит номер пункта меню и по требованию необходимые данные для выполнения требуемой операции.
     * @param input объект класса ValidateInput, используется для ввода данных с консоли.
     * @param tracker объект класса Tracker, является оберткой для массива заявок, используется для работы с заявками.
     */
    public void init(Input input, ITracker tracker, List<UserAction> actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions, output);
            output.accept(actions.size() + ". ======== Exit =========");
            int select = input.askInt("Select: ", actions.size());
            UserAction action = actions.get(select);
            run = action.execute(input, tracker, output);
        }
    }

    /**
     * Метод выводит в консоль пункты меню приложения.
     */
    private void showMenu(List<UserAction> actions, Consumer<String> output) {
        System.out.println("========== Menu ==========");
        for (int index = 0; index < actions.size(); index++) {
            output.accept(index + ". " + actions.get(index).name());
        }
    }

    /**
     * С этого метода начинается работа приложения.
     * @param args - args.
     */
    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Input validate = new ValidateInput(input);
        ITracker tracker = new Tracker();
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
