package ua.goit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.DAO.*;
import ua.goit.controller.HCookedDishController;
import ua.goit.domain.Cook;
import ua.goit.domain.CookedDish;
import ua.goit.domain.Dish;
import ua.goit.domain.Ingredient;
import ua.goit.service.CookedDishService;

import java.util.Date;
import java.util.List;

/**
 * Created by user on 05.12.2016.
 */
@Service
public class CookedDishServiceImpl implements CookedDishService {


    @Autowired
    private CookedDishDao cookedDishDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DishDao dishDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private StorageDao storageDao;

    @Transactional
    public void addCookedDish(String employeeName, String dishName, int orderNumber) {

        CookedDish cookedDish = new CookedDish();
        Dish dish = dishDao.findDishByName(dishName);

        cookedDish.setCook((Cook) employeeDao.findEmployeeByName(employeeName));
        cookedDish.setId(dish.getId());
        cookedDish.setDate(new Date());

        if (orderDao.findOrderById(orderNumber).getDishes().contains(dishDao.findDishByName(dishName))) {

            cookedDish.setOrderNumber(orderNumber);
            List<Ingredient> ingredients = dish.getIngredient();

            for (Ingredient i : ingredients) {
                storageDao.updateQuantity(i.getName(), -1);
            }

            cookedDishDao.addCookedDish(cookedDish);
        } else {
            String s = "Order " + orderNumber + " doesn't contain a dish named " + dishName +
                    ". This dish can't be added to the list of the cooked dishes.";

            throw new RuntimeException(s);
        }
    }

    @Transactional
    public List<CookedDish> getAllCookedDishes() {

        return cookedDishDao.getAllCookedDishes();
    }
}
