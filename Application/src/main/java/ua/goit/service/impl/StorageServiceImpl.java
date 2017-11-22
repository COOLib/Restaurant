package ua.goit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.domain.Ingredient;
import ua.goit.domain.Storage;
import ua.goit.DAO.StorageDao;
import ua.goit.service.IngredientService;
import ua.goit.service.StorageService;
import ua.goit.service.impl.IngredientServiceImpl;

import java.util.List;

/**
 * Created by user on 24.11.2016.
 */
@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageDao storageDao;

    @Autowired
    private IngredientService ingredientService;

    @Transactional
    public Ingredient addIngredientToStorage(String name, int quantity) {

        Ingredient exist = ingredientService.getByName(name);

        if (exist == null) {
            ingredientService.addIngredient(name);
            Ingredient ingredient = ingredientService.getByName(name);
            storageDao.addIngredientToStorage(ingredient, quantity);
        }

        return exist;
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
