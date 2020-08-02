package ru.job4j.tracker;

/**
 * Класс для для получения пользовательского ввода с проверкой корректности ввода.
 *
 * @author Sergey Morozov (morozov.java.job@gmail.com)
 * @version 1.0
 */
public class ValidateInput implements Input {

    /**
     * Переменная хранит другой класс для получения ввода.
     */
    private final Input input;

    /**
     * Конструктор класса.
     * @param input - класс для расширения возможностей.
     */
    public ValidateInput(Input input) {
        this.input = input;
    }

    /**
     * Получение пользовательского ввода в виде строки.
     * @param question - оповещение для пользователя.
     * @return - строка введенная пользователем.
     */
    @Override
    public String askStr(String question) {
        return input.askStr(question);
    }

    /**
     * Получение ввода пользователя в числовом типе.
     * @param question - оповещение пользователя.
     * @return - число введенное пользователем.
     */
    @Override
    public int askInt(String question) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = input.askInt(question);
                invalid = false;
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter validate data again.");
            }
        } while (invalid);
        return value;
    }

    /**
     * Получение ввода пользователя в числовом типе с проверкой корректности.
     * @param question - оповещение пользователя.
     * @param max - предел ввода.
     * @return - пользовательский ввод.
     */
    @Override
    public int askInt(String question, int max) {
        boolean invalid = true;
        int value = -1;
        do {
            try {
                value = input.askInt(question, max);
                invalid = false;
            } catch (IllegalStateException ise) {
                System.out.println("Please select key from menu");
            } catch (NumberFormatException nfe) {
                System.out.println("Please enter validate data again.");
            }
        } while (invalid);
        return value;
    }
}
