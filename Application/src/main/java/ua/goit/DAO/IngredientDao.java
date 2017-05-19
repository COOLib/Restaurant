package ua.goit.DAO;

import ua.goit.domain.Ingredient;

import java.util.List;

/**
 * Created by COOLib on 30.05.2016.
 */
public interface IngredientDao {

    Ingredient findIngredientByName(String name);

    void addIngredient(Ingredient ingredient);

    List<Ingredient> allIngredients();
}