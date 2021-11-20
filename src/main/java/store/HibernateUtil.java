package store;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;

/**
 * Класс HibernateUtil - создает проинициализированный объект SessionFactory.
 *
 * @author Nikolay Polegaev
 * @version 1.0 20.11.2021
 */
public class HibernateUtil {

    private static final SessionFactory SESSION_FACTORY = initSessionFactory();

    private static SessionFactory initSessionFactory() {
        try {
            return new Configuration().configure(
                    new File("src\\main\\resources\\hibernate.cfg.xml"))
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {

        if (SESSION_FACTORY == null) {
            initSessionFactory();
        }

        return SESSION_FACTORY;
    }

    public static void close() {
        getSessionFactory().close();
    }
}