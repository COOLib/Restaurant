package ua.goit.DAO;

import ua.goit.domain.Order;

import java.util.List;

/**
 * Created by COOLib on 01.06.2016.
 */
public interface OrderDao {

    void createNewOrder(Order order);

    void removeOrder(int id);

    void deleteDishFromOrder(String name, int orderNumber);

    void addDishToOrder(int orderNumber, String name);

    void turnToClosed(int id);

    List<Order> getAllOpenedOrders();

    List<Order> getAllClosedOrders();

    Order findOrderById(Integer id);
}