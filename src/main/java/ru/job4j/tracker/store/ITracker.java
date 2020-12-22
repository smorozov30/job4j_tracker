package ru.job4j.tracker.store;

import ru.job4j.tracker.model.Item;

import java.util.List;

/**
 * Интерфейс для класса реализующего хранилище для заявок.
 */
public interface ITracker {

    /**
     * Метод добавляет заявку в систему и возвращает добавленную заявку.
     * @param item - новая заявка для добавления в систему.
     * @return - заявка добавленная в систему.
     */
    Item add(Item item);

    /**
     * Метод заменяет одну заявку другой по id.
     * @param id - старой заявки.
     * @param item - новая заявка.
     * @return - результат замены.
     */
    boolean replace(String id, Item item);

    /**
     * Метод удаляет заявку из системы по id.
     * @param id - удаляемой заявки.
     * @return - результат удаления.
     */
    boolean delete(String id);

    /**
     * Метод возвращает все заявки системы в виде коллекции.
     * @return - коллекция заявок.
     */
    List<Item> findAll();

    /**
     * Метод находит заявки по имени и возвращает их в виде коллекции.
     * @param key - имя заявки для поиска.
     * @return - коллекция найденных заявок.
     */
    List<Item> findByName(String key);

    /**
     * Метод ищет заявку по id.
     * @param id - для поиска заявки.
     * @return - найденная заявка.
     */
    Item findById(String id);
}
