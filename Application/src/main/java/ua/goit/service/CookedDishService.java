package ua.goit.service;

import org.springframework.stereotype.Service;
import ua.goit.domain.CookedDish;

import java.util.List;

/**
 * Created by user on 18.05.2017.
 */
@Service
public interface CookedDishService {

    void addCookedDish(String employeeName, String dishName, int orderNumber);
    List<CookedDish> getAllCookedDishes();
}
