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
import java.util.List;

/**
 * Класс AdRepository - класс с методами для выборки данных из БД.
 * Тестирование ручное.
 *
 * @author Nikolay Polegaev
 * @version 1.0 20.11.2021
 */
public class AdRepository {

    private static final AdRepository INSTANCE = new AdRepository();

    private static final Logger LOG = LogManager.getLogger(AdRepository.class.getName());

    private final SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .buildSessionFactory();

    public static AdRepository getInstance() {
        return INSTANCE;
    }

    public List<Advertisement> showLastDay() {
        List<Advertisement> result = new ArrayList<>();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            result = session.createQuery(
                    "FROM Advertisement WHERE "
                            + "created BETWEEN current_date - 1 AND current_date + 1").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            transaction.rollback();
            LOG.error(e.getMessage(), e);
        } finally {
            session.close();
        }
        return result;
    }

    public List<Advertisement> showWithPhoto() {
        List<Advertisement> result = new ArrayList<>();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            result = session.createQuery("FROM Advertisement WHERE photo != ''").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            transaction.rollback();
            LOG.error(e.getMessage(), e);
        } finally {
            session.close();
        }
        return result;
    }

    public List<Advertisement> showWithBrand(Brand brand) {
        List<Advertisement> result = new ArrayList<>();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            if (brand != null) {
                Query query = session.createQuery(
                        "FROM Advertisement as adv "
                                + "join fetch adv.car as car "
                                + "join fetch car.brand as brand "
                                + "WHERE brand.name = :brn");
                query.setParameter("brn", brand.getName());
                result = query.getResultList();
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            transaction.rollback();
            LOG.error(e.getMessage(), e);
        } finally {
            session.close();
        }
        return result;
    }

    public static void main(String[] args) {
        AdRepository ar = new AdRepository();

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
