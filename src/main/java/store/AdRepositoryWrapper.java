package store;

import model.Advertisement;
import model.Brand;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

/**
 * Класс AdRepositoryWrapper
 *
 * @author Nikolay Polegaev
 * @version 1.0 08.01.2022
 */
public class AdRepositoryWrapper {
    private static final AdRepository INSTANCE = new AdRepository();

    private static final Logger LOG = LogManager.getLogger(AdRepository.class.getName());

    private final SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();

    public static AdRepository getInstance() {
        return INSTANCE;
    }

    public <T> T sessionMethodsWithReturn(final Function<Session, T> command) {
        final Session session = factory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public List<Advertisement> showLastDay() {
        long oneDayInMillis = 24 * 60 * 60 * 1000;
        Date oneDayAgo = new Date(System.currentTimeMillis() - oneDayInMillis);
        return sessionMethodsWithReturn(session -> session.createQuery(
                "FROM Advertisement WHERE "
                        + "created >= :fDate").setParameter("fDate", oneDayAgo).list());
    }

    public List<Advertisement> showWithPhoto() {
        return sessionMethodsWithReturn(session -> session.createQuery(
                "FROM Advertisement WHERE photo != ''").list());

    }

    public List<Advertisement> showWithBrand(Brand brand) {
        List<Advertisement> result = new ArrayList<>();
        if (brand != null) {
            result = sessionMethodsWithReturn(session -> {
                Query query = session.createQuery(
                        "FROM Advertisement as adv "
                                + "join fetch adv.car as car "
                                + "join fetch car.brand as brand "
                                + "WHERE brand.name = :brn");
                query.setParameter("brn", brand.getName());
                return query.getResultList();
            });
        }
        return result;
    }

    public static void main(String[] args) {
        AdRepositoryWrapper ar = new AdRepositoryWrapper();

        System.out.println("\n---показать объявления за последний день---");
        System.out.println(ar.showLastDay());

        System.out.println("\n---показать объявления с фото---");
        System.out.println(ar.showWithPhoto());

        System.out.println("\n---показать объявления определенной марки");
        Brand brand = new Brand();
        brand.setName("Honda");
        System.out.println(ar.showWithBrand(brand));
    }
}
