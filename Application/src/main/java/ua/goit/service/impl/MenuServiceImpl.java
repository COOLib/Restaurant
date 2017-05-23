package ua.goit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.domain.Dish;
import ua.goit.domain.Menu;
import ua.goit.DAO.DishDao;
import ua.goit.DAO.MenuDao;
import ua.goit.service.MenuService;

import java.util.List;

/**
 * Created by user on 01.12.2016.
 */
@Service
public class MenuServiceImpl implements MenuService{

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private DishDao dishDao;

    @Transactional
    public List<Menu> getAllMenus() {

        return menuDao.getAllMenus();
    }

    @Transactional
    public Menu addMenu(Menu menu) {

        Menu newMenu = new Menu();

        newMenu.setName(menu.getName());
        newMenu.setDishes(menu.getDishes());

        menuDao.addMenu(menu);

        return newMenu;
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
    public Dish addDishToMenu(String menuName, String dishName) {

        Dish dish = dishDao.findDishByName(dishName);
        menuDao.addDishToMenu(menuName, dishName);

        return dish;
    }

    @Transactional
    public void deleteDishFromMenu(String menuName, String dishName) {

        menuDao.removeDishFromMenu(menuName, dishName);
    }
}
