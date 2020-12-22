package ru.job4j.tracker.model;

/**
 * Класс преставляющий заявку.
 *
 * @author Sergey Morozov (morozov.java.job@gmail.com)
 * @version 1.0
 */
public class Item {

    /**
     * Идентификационный номер заявки.
     */
    private String id;

    /**
     * Имя заявки.
     */
    private String name;

    /**
     * Конструктор класса Item.
     * @param name - имя заявки.
     */
    public Item(String name) {
        this.name = name;
    }

    /**
     * Возвращает ID заявки.
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Устанавливает ID заявки.
     * @param id - id заявки.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Возвращает имя заявки.
     * @return имя заявки.
     */
    public String getName() {
        return name;
    }
}
