package ua.goit.controller;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.domain.Dish;
import ua.goit.domain.Order;
import ua.goit.domain.Waiter;
import ua.goit.DAO.DishDao;
import ua.goit.DAO.EmployeeDao;
import ua.goit.DAO.OrderDao;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class HOrderController {

    private OrderDao orderDao;
    private EmployeeDao employeeDao;
    private DishDao dishDao;

    @Transactional
    public void addOrder(String waiterName, List<String> dishes, int tableNumber) {

        Order order = new Order();
        order.setWaiter((Waiter) employeeDao.findEmployeeByName(waiterName));
        order.setDishes(createDishes(dishes));
        order.setTableNumber(tableNumber);
        order.setDate(new Date());
        order.setClosed("opened");

        orderDao.createNewOrder(order);
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
    public void addDishToOrder(int orderId, String dishName) {

        orderDao.addDishToOrder(orderId, dishName);
    }

    @Transactional
    public void deleteDishFromOrder(int orderId, String dishName) {

        orderDao.deleteDishFromOrder(dishName, orderId);
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }
}