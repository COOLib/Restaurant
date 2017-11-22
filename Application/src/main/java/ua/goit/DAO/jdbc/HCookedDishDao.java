package ua.goit.DAO.jdbc;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.domain.CookedDish;
import ua.goit.DAO.CookedDishDao;

import java.util.List;

@Component
public class HCookedDishDao implements CookedDishDao {


    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void addCookedDish(CookedDish dish) {

        sessionFactory.getCurrentSession().save(dish);
    }

    @Override
    @Transactional
    public List<CookedDish> getAllCookedDishes() {

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<CookedDish> cookedDishes = session.createCriteria(CookedDish.class).list();

        return cookedDishes;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}