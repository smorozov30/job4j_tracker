package ru.job4j.tracker.action;

/**
 * Абстрактный класс частично реализующий методы интерфейса UserAction.
 *
 * @author Sergey Morozov (morozov.java.job@gmail.com)
 * @version 1.0
 */
public abstract class BaseAction implements UserAction {

    /**
     * Поле хранит имя действия.
     */
    private final String name;

    /**
     * Конструктор класса.
     * @param name - имя для меню.
     */
    protected BaseAction(String name) {
        this.name = name;
    }

    /**
     * Возвращает имя действия.
     * @return - имя для меню.
     */
    @Override
    public String name() {
        return name;
    }
}
