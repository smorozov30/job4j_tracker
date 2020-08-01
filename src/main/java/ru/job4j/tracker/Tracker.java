package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Класс Tracker обертка над коллекцией. Используется как хранилище для заявок.
 *
 * @author Sergey Morozov (morozov.java.job@gmail.com)
 */
public class Tracker implements ITracker {

    /**
     * Константа используемая для генерации случайного id каждой заявки.
     */
    private static final Random RANDOM = new Random();

    /**
     * Коллекция для хранение заявок.
     */
    private List<Item> items = new ArrayList<>();


    /**
     * Добавляет заявку в систему.
     * @param item - новая заявка.
     * @return
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Генерирует уникальный ключ для заявки.
     * Для идентификации каждой заявки создает уникальный ключ.
     * @return уникальный ключ.
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RANDOM.nextLong());
    }

    /**
     * Заменяет заявку в системе по id.
     * @param id - уникальный идентификатор заменяемой заявки.
     * @param item - добавляемая заявка.
     * @return результат замены.
     */
    public boolean replace(String id, Item item) {
        boolean result = false;
            for (int index = 0; index < this.items.size(); index++) {
                if (this.items.get(index).getId().equals(id)) {
                    this.items.set(index, item);
                    result = true;
                    break;
                }
            }
        return result;
    }

    /**
     * Удаляет из системы заявку по уникальному идентификатору.
     * @param id - уникальный идентификатор.
     * @return результат выполнения удаления.
     */
    public boolean delete(String id) {
        boolean result = false;
        for (int index = 0; index < items.size(); index++) {
            if ((this.items.get(index).getId()).equals(id)) {
                this.items.remove(index);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Возвращает все существующие в системе заявки.
     * @return коллекцию заявок.
     */
    public List<Item> findAll() {
        List<Item> all = new ArrayList<>();
        for (int index = 0; index < this.items.size(); index++) {
            all.add(this.items.get(index));
        }
        return all;
    }

    /**
     * Ищет заявки в системе по имени.
     * @param name имя заявки для поиска.
     * @return коллекция найденных заявок.
     */
    public List<Item> findByName(String name) {
        List<Item> result = new ArrayList<>();
        for (int index = 0; index < this.items.size(); index++) {
            if ((this.items.get(index).getName()).equals(name)) {
                result.add(this.items.get(index));
            }
        }
        return result;
    }

    /**
     * Находит заявки в системе по уникальному идентификатору.
     * @param id- уникальный идентификатор для поиска.
     * @return заявка или null, если элемент не найден.
     */
    public Item findById(String id) {
        Item item = null;
        for (int index = 0; index < this.items.size(); index++) {
            if ((this.items.get(index).getId()).equals(id)) {
                item = items.get(index);
                break;
            }
        }
        return item;
    }
}
