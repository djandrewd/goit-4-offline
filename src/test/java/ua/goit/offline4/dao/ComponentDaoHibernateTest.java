package ua.goit.offline4.dao;

import java.math.BigDecimal;
import java.util.Scanner;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import ua.goit.offline4.dao.hibernate.ComponentsDaoHibernate;
import ua.goit.offline4.entity.Component;
import ua.goit.offline4.entity.Pizza;
import ua.goit.offline4.entity.PizzaComponents;

/**
 * HibernateTest.
 *
 * @author Andrey Minov (andrey.minov@playtech.com)
 * @since 2016.12
 */
public class ComponentDaoHibernateTest {

    public static void main(String[] args) {
        String username = System.getProperty("username");
        String password = System.getProperty("password");

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                        .configure("hibernate.cfg.xml")
                        .applySetting("hibernate.connection.username", username)
                        .applySetting("hibernate.connection.password", password)
                        .build();

        Metadata metadata = new MetadataSources(registry).addAnnotatedClass(Component.class)
                        .addAnnotatedClass(PizzaComponents.class)
                        .addAnnotatedClass(Pizza.class)
                        .buildMetadata();
        try (SessionFactory sessionFactory = metadata.buildSessionFactory()) {
            ComponentsDaoHibernate dao = new ComponentsDaoHibernate(sessionFactory);
            //System.out.println(dao.getAll());
            System.out.println(dao.gtByPrize(BigDecimal.valueOf(50)));
            Component cmp = dao.add("tesst", BigDecimal.ONE);
            System.out.println(cmp);
            cmp.setName("tttttttt");
            System.out.println(dao.update(cmp));
            System.out.println(dao.delete(cmp.getId()));
            System.out.println(dao.get(cmp.getId()));
        }
    }
}
