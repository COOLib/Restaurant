package ua.goit.service;

import org.springframework.stereotype.Service;
import ua.goit.domain.Dish;
import ua.goit.domain.Order;

import java.util.List;

/**
 * Created by user on 23.05.2017.
 */
@Service
public interface OrderService {

    Order addOrder(Order order);
    void deleteOrder(int id);
    void turnToClosed(int id);
    Order getById(int id);
    List<Order> getAllClosed();
    List<Order> getAllOpened();
    Dish addDishToOrder(int orderId, String dishName);
    void deleteDishFromOrder(int orderId, String dishName);

}
