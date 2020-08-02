package ru.job4j.tracker;

/**
 * Интерфейс для классов реализующих ввод данных в систему.
 *
 * @author Sergey Morozov (morozov.java.job@gmail.com)
 * @version 1.0
 */
public interface Input {

    /**
     * Получает ввод пользователся и возвращает его в виде строки.
     * @param question - сообщение подсказка для вывода пользователю
     * @return - строка введенная пользователем.
     */
    String askStr(String question);

    /**
     * Получает ввод пользователся и возвращает его в виде числа.
     * @param question - сообщение подсказка для вывода пользователю
     * @return - число введенное пользователем.
     */
    int askInt(String question);

    /**
     * Получает ввод пользователся и возвращает его в виде числа, которое было проверено на корректность.
     * @param question - сообщение подсказка для вывода пользователю
     * @return - число введенное пользователем.
     */
    int askInt(String question, int max);
}
