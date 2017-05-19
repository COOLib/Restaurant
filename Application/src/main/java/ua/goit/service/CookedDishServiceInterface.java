package ua.goit.service;

import ua.goit.domain.CookedDish;

import java.util.List;

/**
 * Created by user on 18.05.2017.
 */
public interface CookedDishServiceInterface {

    public void addCookedDish(String employeeName, String dishName, int orderNumber);
    public List<CookedDish> getAllCookedDishes();
}
