package ua.goit.DAO;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ua.goit.domain.Dish;

import java.util.List;

/**
 * Created by COOLib on 29.05.2016.
 */

public interface DishDao {

    void addDish(Dish dish);

    void removeDish(String name);

    Dish findDishByName(String name);

    List<Dish> getAllDishes();
}