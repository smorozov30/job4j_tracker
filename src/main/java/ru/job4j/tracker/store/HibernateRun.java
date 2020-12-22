package ru.job4j.tracker.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.tracker.model.NewItem;

import java.sql.Timestamp;
import java.util.List;

public class HibernateRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            NewItem item = create(new NewItem("Learn Hibernate"), sf);
            System.out.println(item);
            item.setName("Learn Hibernate 5.");
            item.setDescription("Learn Hibernate Description.");
            item.setCreated( new Timestamp(1459510232000L));
            update(item, sf);
            System.out.println(item);
            NewItem rsl = findById(item.getId(), sf);
            System.out.println(rsl);
            delete(rsl.getId(), sf);
            List<NewItem> list = findAll(sf);
            for (NewItem it : list) {
                System.out.println(it);
            }
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static NewItem create(NewItem item, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    public static void update(NewItem item, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.update(item);
        session.getTransaction().commit();
        session.close();
    }

    public static void delete(int id, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        NewItem item = new NewItem(null);
        item.setId(id);
        session.delete(item);
        session.getTransaction().commit();
        session.close();
    }

    public static List<NewItem> findAll(SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        List result = session.createQuery("FROM ru.job4j.tracker.model.NewItem").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public static NewItem findById(Integer id, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        NewItem result = session.get(NewItem.class, id);
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
