package ua.goit.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.goit.domain.Ingredient;
import ua.goit.domain.Storage;
import ua.goit.DAO.StorageDao;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurant")
public class HStorageController {

    private StorageDao storageDao;
    private HIngredientController ingredientController;

    public void addIngredientToStorage(String name, int quantity) {

        ingredientController.addIngredient(name);
        Ingredient ingredient = ingredientController.getByName(name);
        storageDao.addIngredientToStorage(ingredient, quantity);
    }

    public void removeIngredientFromStorage(String name) {

        storageDao.removeIngredientFromStorage(name);
    }

    public List<Storage> getAllIngredients() {

        return storageDao.getAllIngredients();
    }

    public List<Storage> getEndingIngredients() {

        return storageDao.getAllEndingIngredients();
    }

    public Storage getByIngredientName(String name) {

        return storageDao.findIngredientByName(name);
    }

    public void updateQuantity(String ingredientName, int addingAmount) {

        storageDao.updateQuantity(ingredientName, addingAmount);
    }

    public void setStorageDao(StorageDao storageDao) {
        this.storageDao = storageDao;
    }

    public void setIngredientController(HIngredientController ingredientController) {
        this.ingredientController = ingredientController;
    }
}