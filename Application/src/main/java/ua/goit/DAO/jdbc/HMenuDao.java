package ua.goit.DAO.jdbc;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.domain.Dish;
import ua.goit.domain.Menu;
import ua.goit.DAO.DishDao;
import ua.goit.DAO.MenuDao;

import java.util.List;

@Component
public class HMenuDao implements MenuDao {

    private SessionFactory sessionFactory;

    @Autowired
    private DishDao dishDao;

    @Override
    @Transactional
    public void addMenu(Menu menu) {
        sessionFactory.getCurrentSession().save(menu);
    }

    @Override
    @Transactional
    public void removeMenu(String name) {

        Menu menu = findMenuByName(name);
        sessionFactory.getCurrentSession().delete(menu);
    }

    @Override
    @Transactional
    public Menu findMenuByName(String name) {

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(Menu.class)
                .add(Restrictions.like("name", name));

        Menu menu = (Menu) criteria.uniqueResult();

        if (menu == null) {
            throw new RuntimeException("Cannot find menu with name " + name);
        }
        return menu;
    }

    @Override
    @Transactional
    public List<Menu> getAllMenus() {

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Menu> menus= session.createCriteria(Menu.class).list();
        return menus;
    }

    @Override
    @Transactional
    public void addDishToMenu(String menuName, String dishName) {

        Menu menu = findMenuByName(menuName);

        Dish dish = dishDao.findDishByName(dishName);
        menu.getDishes().add(dish);
    }

    @Override
    @Transactional
    public void removeDishFromMenu(String menuName, String dishName) {

        Menu menu = findMenuByName(menuName);
        Dish dish = dishDao.findDishByName(dishName);

        menu.getDishes().remove(dish);
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}