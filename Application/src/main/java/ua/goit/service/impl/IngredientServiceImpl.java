package ua.goit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.domain.Ingredient;
import ua.goit.DAO.IngredientDao;
import ua.goit.service.IngredientService;

import java.util.List;

/**
 * Created by user on 24.11.2016.
 */
@Service
public class IngredientServiceImpl  implements IngredientService {

    @Autowired
    private IngredientDao ingredientDao;

    @Transactional
    public Ingredient getByName(String name) {

        return ingredientDao.findIngredientByName(name);
    }

    @Transactional
    public Ingredient addIngredient(String name) {

        Ingredient ingredient = new Ingredient();
        ingredient.setName(name);
        ingredientDao.addIngredient(ingredient);

        return ingredient;
    }

    @Transactional
    public List<Ingredient> getAllIngredients() {

        return ingredientDao.allIngredients();
    }
}
