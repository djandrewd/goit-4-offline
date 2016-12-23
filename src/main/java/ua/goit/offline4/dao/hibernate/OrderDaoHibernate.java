package ua.goit.offline4.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import ua.goit.offline4.dao.OrderDao;
import ua.goit.offline4.entity.Order;

/**
 * HibernateOrderDao.
 *
 * @author Andrey Minov (andrey.minov@playtech.com)
 * @since 2016.12
 */
public class OrderDaoHibernate
    implements OrderDao {

    private SessionFactory factory;

    public OrderDaoHibernate(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<Order> getAll() {
        // factory.getCurrentSession();
        try (Session session = factory.openSession()) {
            return session.createQuery("from Order", Order.class).list();
        }
    }
}
