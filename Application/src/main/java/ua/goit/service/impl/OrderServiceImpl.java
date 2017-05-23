package ua.goit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.domain.Dish;
import ua.goit.domain.Order;
import ua.goit.domain.Waiter;
import ua.goit.DAO.DishDao;
import ua.goit.DAO.OrderDao;
import ua.goit.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by user on 01.12.2016.
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private DishDao dishDao;

    @Transactional
    public Order addOrder(Order order) {

        Order newOrder = new Order();
        newOrder.setWaiter((Waiter) order.getWaiter());
        newOrder.setDishes(order.getDishes());
        newOrder.setTableNumber(order.getTableNumber());
        newOrder.setDate(new Date());
        newOrder.setClosed("opened");

        orderDao.createNewOrder(order);
        return newOrder;
    }

    private List<Dish> createDishes(List<String> dishes) {

        List<Dish> result = dishes.stream().map(dishName -> dishDao.findDishByName(dishName)).collect(Collectors.toList());

        return result;
    }

    @Transactional
    public void deleteOrder(int id) {

        orderDao.removeOrder(id);
    }

    @Transactional
    public void turnToClosed(int id) {

        orderDao.turnToClosed(id);
    }

    @Transactional
    public Order getById(int id) {

        return orderDao.findOrderById(id);
    }

    @Transactional
    public List<Order> getAllClosed() {

        return orderDao.getAllClosedOrders();
    }

    @Transactional
    public List<Order> getAllOpened() {

        return orderDao.getAllOpenedOrders();
    }

    @Transactional
    public Dish addDishToOrder(int orderId, String dishName) {

        orderDao.addDishToOrder(orderId, dishName);

        Dish dish = dishDao.findDishByName(dishName);

        return dish;
    }

    @Transactional
    public void deleteDishFromOrder(int orderId, String dishName) {

        orderDao.deleteDishFromOrder(dishName, orderId);
    }
}
