package ua.goit.DAO.jdbc;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.domain.Dish;
import ua.goit.domain.Order;
import ua.goit.DAO.DishDao;
import ua.goit.DAO.OrderDao;

import java.util.List;

@Component
public class HOrderDao implements OrderDao {

    private SessionFactory sessionFactory;

    @Autowired
    private DishDao dishDao;


    @Override
    @Transactional
    public void createNewOrder(Order order) {
        sessionFactory.getCurrentSession().save(order);
    }

    @Override
    @Transactional
    public void removeOrder(int id) {

        Order order = findOrderById(id);
        sessionFactory.getCurrentSession().delete(order);
    }

    @Override
    @Transactional
    public void addDishToOrder(int orderNumber, String name) {

        Order order = findOrderById(orderNumber);
        Dish dish = dishDao.findDishByName(name);

        if (order.getIsClosed().equals("opened")) {

            order.getDishes().add(dish);
        } else {
            throw new RuntimeException("The order " + orderNumber + " is already closed");
        }
    }

    @Override
    @Transactional
    public void deleteDishFromOrder(String name, int orderNumber) {

        Order order = findOrderById(orderNumber);
        Dish dish = dishDao.findDishByName(name);

        if (order.getIsClosed().equals("opened")) {

            order.getDishes().remove(dish);
        } else {

            throw new RuntimeException("The order " + orderNumber + " is already closed");
        }
    }

    @Override
    @Transactional
    public void turnToClosed(int id) {

        Order order = findOrderById(id);
        order.setClosed("closed");

    }

    @Override
    @Transactional
    public List<Order> getAllOpenedOrders() {

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        List<Order> allOpened = session.createCriteria(Order.class)
                .add(Restrictions.like("isClosed", "opened"))
                .list();
        return allOpened;
    }

    @Override
    @Transactional
    public List<Order> getAllClosedOrders() {

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        List<Order> allClosed = session.createCriteria(Order.class)
                .add(Restrictions.like("isClosed", "closed"))
                .list();
        return allClosed;

    }

    @Override
    @Transactional
    public Order findOrderById(Integer id) {

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(Order.class)
                .add(Restrictions.eq("number", id));

        Order order = (Order) criteria.uniqueResult();

        if (id == null) {

            throw new RuntimeException("Cannot find order with name " + id);
        }
        return order;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}