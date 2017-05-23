package ua.goit.service;

import ua.goit.domain.Dish;
import ua.goit.domain.Menu;

import java.util.List;

/**
 * Created by user on 23.05.2017.
 */
public interface MenuService {

    public List<Menu> getAllMenus();
    public Menu addMenu(Menu menu);
    public void deleteMenu(String name);
    public Menu getByName(String name);
    public Dish addDishToMenu(String menuName, String dishName);
    public void deleteDishFromMenu(String menuName, String dishName);

}
