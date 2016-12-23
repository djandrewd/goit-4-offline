package ua.goit.offline4.dao.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.sql.DataSource;

import ua.goit.offline4.dao.PizzaDao;
import ua.goit.offline4.entity.Component;
import ua.goit.offline4.entity.PizzaComponents;
import ua.goit.offline4.entity.Pizza;

/**
 * PizzaDaoJbdc.
 *
 * @author Andrey Minov (andrey.minov@playtech.com)
 * @since 2016.12
 */
public class PizzaDaoJbdc
    implements PizzaDao {

    private static final String GET_BY_ID = "select id, name, prize from pizzeria.pizza where id = ?";
    private static final String GET_ALL = "select id, name, prize from pizzeria.pizza";
    private static final String GET_COMPONENTS_ALL = "select c.id, c.name, c.prize, pc.amount from pizzeria.components c join "
                                                     + " pizzeria.pizza_components pc on (c.id = pc.component_id) where pc.pizza_id = ?";
    private static final String ADD_NEW = "insert into pizzeria.pizza(name, prize) values (?, ?) returning id";
    private static final String ADD_COMPONENTS_NEW = "insert into pizzeria.pizza_components(component_id, pizza_id, amount) values (?, ?, ?)";

    private DataSource dataSource;

    public PizzaDaoJbdc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Pizza get(long id)
        throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(GET_BY_ID)) {
                ps.setLong(1, id);
                try (ResultSet resultSet = ps.executeQuery()) {
                    if (!resultSet.next()) {
                        return null;
                    }
                    Pizza pizza = new Pizza();
                    pizza.setId(resultSet.getLong(1));
                    pizza.setName(resultSet.getString(2));
                    pizza.setPrize(resultSet.getBigDecimal(3));
                    //pizza.setComponents(getComponents(connection, pizza.getId()));
                    return pizza;
                }

            }
        }
    }

    @Override
    public Collection<Pizza> getAll()
        throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            try (Statement ps = connection.createStatement()) {
                Collection<Pizza> pizzas = new ArrayList<>();
                try (ResultSet resultSet = ps.executeQuery(GET_ALL)) {
                    while (resultSet.next()) {
                        Pizza pizza = new Pizza();
                        pizza.setId(resultSet.getLong(1));
                        pizza.setName(resultSet.getString(2));
                        pizza.setPrize(resultSet.getBigDecimal(3));
                        pizzas.add(pizza);
                    }
                }

                /*for (Pizza pizza : pizzas) {
                    pizza.setComponents(getComponents(connection, pizza.getId()));
                }*/
                return pizzas;
            }
        }
    }

    @Override
    public Pizza add(String name, BigDecimal prize, List<PizzaComponents> components)
        throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            try {
                Pizza pizza = new Pizza();
                try (PreparedStatement ps = connection.prepareStatement(ADD_NEW)) {
                    ps.setString(1, name);
                    ps.setBigDecimal(2, prize);
                    if (!ps.execute()) {
                        return null;
                    }
                    try (ResultSet resultSet = ps.getResultSet()) {
                        if (!resultSet.next()) {
                            return null;
                        }
                        pizza.setId(resultSet.getLong(1));
                    }

                }
                pizza.setName(name);
                pizza.setPrize(prize);
                try (PreparedStatement ps = connection.prepareStatement(ADD_COMPONENTS_NEW)) {
                    for (PizzaComponents amount : components) {
                        ps.setLong(1, amount.getComponent().getId());
                        ps.setLong(2, pizza.getId());
                        ps.setBigDecimal(3, amount.getAmount());
                        ps.addBatch();
                    }
                    ps.executeBatch();
                }
                //pizza.setComponents(components);
                connection.commit();
                return pizza;
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            } catch (Exception e) {
                connection.rollback();
                throw new RuntimeException(e.getMessage(), e);
            }
        }
    }

    @Override
    public void update(Pizza pizza)
        throws SQLException {

    }

    @Override
    public void delete(Pizza pizza)
        throws SQLException {

    }

    @Override
    public void addComponent(long id, PizzaComponents component)
        throws SQLException {

    }

    @Override
    public void removeComponent(long id, PizzaComponents component)
        throws SQLException {}

    private List<PizzaComponents> getComponents(Connection connection, long pizzaId)
        throws SQLException {
        try (PreparedStatement psC = connection.prepareStatement(GET_COMPONENTS_ALL)) {
            psC.setLong(1, pizzaId);
            try (ResultSet resultSet = psC.executeQuery()) {
                List<PizzaComponents> components = new ArrayList<>();
                while (resultSet.next()) {
                    Component component = new Component();
                    component.setId(resultSet.getLong(1));
                    component.setName(resultSet.getString(2));
                    component.setPrize(resultSet.getBigDecimal(3));
                    BigDecimal amount = resultSet.getBigDecimal(4);
                    PizzaComponents componentAmount = new PizzaComponents();
                    componentAmount.setComponent(component);
                    componentAmount.setAmount(amount);
                    components.add(componentAmount);
                }
                return components;
            }
        }
    }
}
