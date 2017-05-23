package ua.goit.controller;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.goit.domain.Dish;
import ua.goit.domain.Menu;
import ua.goit.DAO.DishDao;
import ua.goit.DAO.MenuDao;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurant")
public class HMenuController {


    private MenuDao menuDao;
    private DishDao dishDao;

    @Transactional
    public List<Menu> getAllMenus() {

        return menuDao.getAllMenus();
    }

    @Transactional
    public void addMenu(String name, List<Dish> dishes) {

        Menu menu = new Menu();

        menu.setName(name);
        menu.setDishes(dishes);

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

    public void setMenuDao(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }
}