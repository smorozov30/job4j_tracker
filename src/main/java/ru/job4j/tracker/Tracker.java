package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tracker implements ITracker {
    /**
     * Массив для хранение заявок.
     */
    private List<Item> items = new ArrayList<>();

    /**
     * Константа используемая для генерации случайного id каждой заявки.
     */
    private static final Random RANDOM = new Random();

    /**
     * Метод добавляет заявку в массив заявок.
     * @param item Новая заявка, котрорую нужно добавить в масиив.
     * @return
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Для идентификации каждой заявки создает уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RANDOM.nextLong());
    }

    /**
     * Метод заменет один элемент в массиве - другим.
     * @param id Уникальный идентификатор элемента который нужно заменить.
     * @param item Элемент который нужно вставить в массив.
     * @return Результат замены.
     */
    public boolean replace(String id, Item item) {
        boolean result = false;
        if (this.findById(id) != null) {
            for (int index = 0; index < this.items.size(); index++) {
                if (this.items.get(index).getId().equals(id)) {
                    this.items.set(index, item);
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Метод удаляет из массива элемент по уникальному идентификатору.
     * @param id Уникальный идентификатор.
     * @return Результат выполнения удаления.
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
     * Метод копирует заполненые ячейки массива items в новый массив и возвращает его.
     * @return Масиив объектов.
     */
    public List<Item> findAll() {
        List<Item> all = new ArrayList<>();
        for (int index = 0; index < this.items.size(); index++) {
            all.add(this.items.get(index));
        }
        return all;
    }

    /**
     * Метод ищет элементы в массиве items по имени и копиркет их в новый массив.
     * @param name Имя объекта для поиска.
     * @return Массив объектов.
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
     * Метод находит элемент в массиве по уникальному идентификатору.
     * @param id Уникальный идентификатор для поиска.
     * @return Элемент массива или null, если элемент не найден.
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
