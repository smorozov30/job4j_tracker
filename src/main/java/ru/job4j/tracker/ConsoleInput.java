package ru.job4j.tracker;

import java.util.Scanner;

/**
 * Класс предоставляющий возможность консольной работы с системой заявок.
 *
 * @author Sergey Morozov (morozov.java.job@gmail.com)
 * @version 1.0
 */
public class ConsoleInput implements Input {

    /**
     * Поле предоставляет поток ввода с консоли.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Возвращает ввод пользователя в консоли.
     * @param question - Уведомление на консоли для пользователя.
     * @return - строку введенную пользователем.
     */
    @Override
    public String askStr(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }

    /**
     * Возвращает ввод пользователя в консоли в виде числа.
     * @param question - Уведомление на консоли для пользователя.
     * @return - число введенное пользователем.
     */
    @Override
    public int askInt(String question) {
        return Integer.valueOf(askStr(question));
    }

    /**
     * Возвращает число введенное пользователем в консоли с проверкой его на правильность.
     * @param question - оповещение для пользователя.
     * @param max - предел вводимых пользователем значений.
     * @return - число введенное пользователем.
     */
    @Override
    public int askInt(String question, int max) {
        String valueStr = this.askStr(question);
        hasValid(valueStr, max);
        int select = Integer.valueOf(valueStr);
        return select;
    }

    /**
     * Проверяет корректность пользовательского ввода.
     * @param valueStr - пользовательский ввод.
     * @param max - предельная граница для числового ввода.
     * @return - результат проверки корректности ввода.
     */
    private boolean hasValid(String valueStr, int max) {
        if (!checkInt(valueStr)) {
            throw new NumberFormatException();
        }
        if (!checkBound(valueStr, max)) {
            throw new IllegalStateException();
        }
        return true;
    }

    /**
     * Проверяет ввод пользователя на соответсвие числовому типу.
     * @param valueStr - строка ввод пользователя.
     * @return - результат проверки.
     */
    private boolean checkInt(String valueStr) {
        Integer.valueOf(valueStr);
        return true;
    }

    /**
     * Проверяет корректность ввода пользователя по числовым границам.
     * @param valueStr - ввод пользователя.
     * @param max - граница числового ввода.
     * @return - результат проверки.
     */
    private boolean checkBound(String valueStr, int max) {
        int select = Integer.valueOf(valueStr);
        if (select < 0 || select >= max) {
            throw new IllegalStateException(String.format("Out of about %s > [0, %s]", select, max));
        }
        return true;
    }
}
