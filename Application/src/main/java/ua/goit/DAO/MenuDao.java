package ua.goit.DAO;

import ua.goit.domain.Menu;

import java.util.List;

/**
 * Created by COOLib on 31.05.2016.
 */
public interface MenuDao {

    void addMenu(Menu menu);

    void removeMenu(String name);

    Menu findMenuByName(String name);

    List<Menu> getAllMenus();

    void addDishToMenu(String menuName, String dishName);

    void removeDishFromMenu(String menuName, String dishName);
}