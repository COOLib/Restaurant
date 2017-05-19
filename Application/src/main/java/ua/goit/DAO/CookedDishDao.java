package ua.goit.DAO;

import ua.goit.domain.CookedDish;

import java.util.List;

/**
 * Created by COOLib on 29.05.2016.
 */
public interface CookedDishDao {

    void addCookedDish(CookedDish dish);

    List<CookedDish> getAllCookedDishes();
}