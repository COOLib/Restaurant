package ua.goit.service;

import org.springframework.stereotype.Service;
import ua.goit.domain.Dish;

import java.util.List;

/**
 * Created by user on 23.05.2017.
 */
@Service
public interface DishService {

    Dish addDish(Dish dish);
    Dish getByName(String name);
    List<Dish> getAllDishes();
    void deleteDish(String name);
}
