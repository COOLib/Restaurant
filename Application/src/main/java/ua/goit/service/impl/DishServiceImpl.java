package ua.goit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.domain.Dish;
import ua.goit.DAO.DishDao;
import ua.goit.service.DishService;

import java.util.List;

/**
 * Created by user on 16.11.2016.
 */
@SuppressWarnings("SpringJavaAutowiringInspection")
@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishDao dishDao;

    @Transactional
    public Dish addDish(Dish dish) {


        dishDao.addDish(dish);
        return dish;
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
