package ua.goit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.domain.Ingredient;
import ua.goit.domain.Storage;
import ua.goit.DAO.StorageDao;

import java.util.List;

/**
 * Created by user on 24.11.2016.
 */
@Service
public class StorageService {

    @Autowired
    private StorageDao storageDao;

    @Autowired
    private IngredientService ingredientService;

    @Transactional
    public void addIngredientToStorage(String name, int quantity) {

        Ingredient exist = new Ingredient();
        exist = ingredientService.getByName(name);

        if (exist == null) {
            ingredientService.addIngredient(name);
            Ingredient ingredient = ingredientService.getByName(name);
            storageDao.addIngredientToStorage(ingredient, quantity);
        }
    }

    @Transactional
    public void removeIngredientFromStorage(String name) {

        storageDao.removeIngredientFromStorage(name);
    }

    @Transactional
    public List<Storage> getAllIngredients() {

        return storageDao.getAllIngredients();
    }

    @Transactional
    public List<Storage> getEndingIngredients() {

        return storageDao.getAllEndingIngredients();
    }

    @Transactional
    public Storage getByIngredientName(String name) {

        return storageDao.findIngredientByName(name);
    }

    @Transactional
    public void updateQuantity(String ingredientName, int addingAmount) {

        storageDao.updateQuantity(ingredientName, addingAmount);
    }
}
