package ua.goit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.domain.Menu;
import ua.goit.DAO.DishDao;
import ua.goit.DAO.MenuDao;

import java.util.List;

/**
 * Created by user on 01.12.2016.
 */
@Service
public class MenuService {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private DishDao dishDao;

    @Transactional
    public List<Menu> getAllMenus() {

        return menuDao.getAllMenus();
    }

    @Transactional
    public void addMenu(Menu menu) {

        Menu newMenu = new Menu();

        newMenu.setName(menu.getName());
        newMenu.setDishes(menu.getDishes());

        menuDao.addMenu(menu);
    }

    @Transactional
    public void deleteMenu(String name) {

        menuDao.removeMenu(name);
    }

    @Transactional
    public Menu getByName(String name) {

        return menuDao.findMenuByName(name);
    }

    @Transactional
    public void addDishToMenu(String menuName, String dishName) {

        menuDao.addDishToMenu(menuName, dishName);
    }

    @Transactional
    public void deleteDishFromMenu(String menuName, String dishName) {

        menuDao.removeDishFromMenu(menuName, dishName);
    }
}
