package ru.job4j.newtracker.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.newtracker.model.Item;

import java.util.List;
import java.util.function.Function;

public class HibernateTracker implements Tracker {
    private final SessionFactory sf;

    private HibernateTracker() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    private static final class Lazy {
        private static final Tracker INST = new HibernateTracker();
    }

    public static Tracker instOf() {
        return HibernateTracker.Lazy.INST;
    }

    @Override
    public Item add(Item item) {
        execute(session -> session.save(item));
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        Item temp = execute(session -> {
            Item i = session.get(Item.class, id);
            i.setName(item.getName());
            session.update(i);
            return i;
        });
        return temp != null;
    }

    @Override
    public boolean delete(String id) {
        Item item = execute(session -> {
            Item temp = session.get(Item.class, Integer.parseInt(id));
            session.remove(temp);
            return temp;
        });
        return item != null;
    }

    @Override
    public List<Item> findAll() {
        return execute(session -> session.createQuery("FROM Item").list());
    }

    @Override
    public List<Item> findByName(String key) {
        return execute(session -> session.createQuery("FROM Item WHERE name = :key").setParameter("key", key).list());
    }

    @Override
    public Item findById(String id) {
        List<Item> items = execute(session -> session.createQuery("FROM Item WHERE id = :id").setParameter("id", Integer.parseInt(id)).list());
        return items.stream().findFirst().orElse(null);
    }

    private <T> T execute(final Function<Session, T> command) {
        T result = null;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            result = command.apply(session);
            session.getTransaction().commit();
        }
        return result;
    }
}
