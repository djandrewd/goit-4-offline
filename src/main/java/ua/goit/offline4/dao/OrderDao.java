package ua.goit.offline4.dao;

import java.util.List;

import ua.goit.offline4.entity.Order;

/**
 * OrderDao.
 *
 * @author Andrey Minov (andrey.minov@playtech.com)
 * @since 2016.12
 */
public interface OrderDao {

    List<Order> getAll();
}
