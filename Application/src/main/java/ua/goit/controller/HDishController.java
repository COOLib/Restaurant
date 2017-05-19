package ua.goit.controller;

import org.springframework.transaction.annotation.Transactional;
import ua.goit.domain.Category;
import ua.goit.domain.Dish;
import ua.goit.domain.Ingredient;
import ua.goit.DAO.DishDao;

import java.util.List;

public class HDishController {

    private DishDao dishDao;

    @Transactional
    public void addDish(String dishName, Category category, int price, int weight, List<Ingredient> ingredients) {

        Dish dish = new Dish();

        dish.setName(dishName);
        dish.setCategory(category);
        dish.setPrice(price);
        dish.setWeight(weight);
        dish.setIngredient(ingredients);

        dishDao.addDish(dish);
    }

    @Transactional
    public void deleteDish(String name) {

        dishDao.removeDish(name);
    }

    @Transactional
    public Dish getByName(String name) {

        return dishDao.findDishByName(name);
    }

    @Transactional
    public List<Dish> getAllDishes() {

        return dishDao.getAllDishes();
    }

    public void setDishDao(DishDao dishDao) {
        this.dishDao = dishDao;
    }
}