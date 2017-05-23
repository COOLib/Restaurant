package ua.goit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.goit.domain.Ingredient;
import ua.goit.service.IngredientService;
import ua.goit.service.impl.IngredientServiceImpl;

@RestController
@RequestMapping(value = "/restaurant")
public class HIngredientController {

    @Autowired
    private IngredientService ingredientService;

    @Transactional
    public Ingredient getByName(String name) {

        return ingredientService.getByName(name);
    }

    @Transactional
    public void addIngredient(String name) {

        ingredientService.addIngredient(name);
    }
}