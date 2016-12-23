package ua.goit.offline4.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ua.goit.offline4.entity.Component;
import ua.goit.offline4.entity.Pizza;
import ua.goit.offline4.entity.PizzaComponents;

/**
 * HibernteTestConnection.
 *
 * @author Andrey Minov (andrey.minov@playtech.com)
 * @since 2016.12
 */
public class HibernteTestConnection {

    public static void main(String[] args) {
        // SessionFactory
        //
        Configuration configuration = new Configuration().addAnnotatedClass(Component.class)
                        .addAnnotatedClass(PizzaComponents.class)
                        .addAnnotatedClass(Pizza.class)
                        .configure("hibernate.cfg.xml");
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            try (Session session = sessionFactory.openSession()) {
                System.out.println(session.get(Component.class, 1L));
            }
        }

    }
}
