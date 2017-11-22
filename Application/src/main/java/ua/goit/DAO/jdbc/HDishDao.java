package ua.goit.DAO.jdbc;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.domain.Dish;
import ua.goit.DAO.DishDao;

import java.util.List;

@Component
public class HDishDao implements DishDao {

    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void addDish(Dish dish) {

        sessionFactory.getCurrentSession().save(dish);
    }

    @Override
    @Transactional
    public void removeDish(String name) {

        Dish dish = findDishByName(name);
        sessionFactory.getCurrentSession().delete(dish);
    }

    @Override
    @Transactional
    public Dish findDishByName(String name) {

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(Dish.class)
                .add(Restrictions.like("name", name));

        Dish dish = (Dish) criteria.uniqueResult();

        if (dish == null) {
            throw new RuntimeException("Cannot find dish with name " + name);
        }
        return dish;
    }

    @Override
    @Transactional
    public List<Dish> getAllDishes() {

        Session session = sessionFactory.getCurrentSession();

        List<Dish> dishes = session.createCriteria(Dish.class).list();
        return dishes;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}