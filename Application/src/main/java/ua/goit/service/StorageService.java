package ua.goit.service;

import org.springframework.stereotype.Service;
import ua.goit.domain.Ingredient;
import ua.goit.domain.Storage;

import java.util.List;

/**
 * Created by user on 23.05.2017.
 */
@Service
public interface StorageService {

    Ingredient addIngredientToStorage(String name, int quantity);
    void removeIngredientFromStorage(String name);
    List<Storage> getAllIngredients();
    List<Storage> getEndingIngredients();
    Storage getByIngredientName(String name);
    void updateQuantity(String ingredientName, int addingAmount);

}
