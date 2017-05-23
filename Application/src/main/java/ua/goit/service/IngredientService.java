package ua.goit.service;

import org.springframework.stereotype.Service;
import ua.goit.domain.Ingredient;

import java.util.List;

/**
 * Created by user on 23.05.2017.
 */
@Service
public interface IngredientService {
    Ingredient getByName(String name);
    Ingredient addIngredient(String name);
    List<Ingredient> getAllIngredients();
}
