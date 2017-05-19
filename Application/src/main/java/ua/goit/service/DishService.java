package ua.goit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.domain.Dish;
import ua.goit.DAO.DishDao;

import java.util.List;

/**
 * Created by user on 16.11.2016.
 */
@Service
public class DishService {

    @Autowired
    private DishDao dishDao;

    @Transactional
    public Dish addDish(Dish dish) {

        Dish newDish = new Dish();

        newDish.setName(dish.getName());
        newDish.setCategory(dish.getCategory());
        newDish.setIngredient(dish.getIngredient());
        newDish.setPrice(dish.getPrice());
        newDish.setWeight(dish.getWeight());

        dishDao.addDish(newDish);
        return newDish;
    }

    @Transactional
    public Dish getByName(String name) {

        return dishDao.findDishByName(name);
    }

    @Transactional
    public List<Dish> getAllDishes() {

        return dishDao.getAllDishes();
    }

    @Transactional
    public void deleteDish(String name) {

        dishDao.removeDish(name);
    }
}
