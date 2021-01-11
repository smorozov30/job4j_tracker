package ru.job4j.newtracker.io.input;

/**
 * Класс для тестирования кода иммитирующий ввод пользователя.
 *
 * @author Sergey Morozov (morozov.java.job@gmail.com)
 * @version 1.0
 */
public class StubInput implements Input {

    /**
     * Поле хранить ответы на запросы системы при тестировании.
     */
    private String[] answers;

    /**
     * Поле хранить позицию последнего ответа системе на завпрос ввода пользователя.
     */
    private int position = 0;

    /**
     * Конструктор класса.
     * @param answers - ответы системе при запросе ввода полльзователя.
     */
    public StubInput(String[] answers) {
        this.answers = answers;
    }

    /**
     * Возвращает ответ в виде строки.
     * @param question - сообщение подсказка для вывода пользователю.
     * @return строка иммитация ввода пользователя.
     */
    @Override
    public String askStr(String question) {
        return answers[position++];
    }

    /**
     * Метод возвращает иммитацию ввода в виде числа.
     * @param question - сообщение подсказка для вывода пользователю.
     * @return число иммитация ввода пользователя.
     */
    @Override
    public int askInt(String question) {
        return Integer.parseInt(askStr(question));
    }

    /**
     * Метод возвращает иммитацию ввода в виде числа, с проверкой на корректность.
     * @param question - сообщение подсказка для вывода пользователю.
     * @return число иммитация ввода пользователя.
     */
    @Override
    public int askInt(String question, int max) throws IllegalStateException {
        int result = askInt(question);
        if (result > max) {
            throw new IllegalStateException();
        }
        return result;
    }
}
