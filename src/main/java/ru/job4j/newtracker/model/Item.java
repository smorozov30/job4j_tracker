package ru.job4j.newtracker.model;

import javax.persistence.*;
import java.util.Objects;

/**
 * Класс преставляющий заявку.
 *
 * @author Sergey Morozov (morozov.java.job@gmail.com)
 * @version 1.0
 */

@Entity
@Table(name = "items")
public class Item {

    /**
     * Идентификационный номер заявки.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Имя заявки.
     */
    private String name;

    /**
     * Конструктор класса Item.
     * @param name - имя заявки.
     */
    public static Item of(String name) {
        Item item = new Item();
        item.name = name;
        return item;
    }

    /**
     * Возвращает ID заявки.
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Устанавливает ID заявки.
     * @param id - id заявки.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Возвращает имя заявки.
     * @return имя заявки.
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
