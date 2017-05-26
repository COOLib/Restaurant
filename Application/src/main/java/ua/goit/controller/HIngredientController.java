package ua.goit.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ua.goit.domain.Ingredient;
import ua.goit.service.IngredientService;
import ua.goit.service.impl.IngredientServiceImpl;

import java.io.IOException;

@RestController
@RequestMapping(value = "/restaurant/ingredient")
public class HIngredientController {

    @Autowired
    private IngredientService ingredientService;
    private static HttpHeaders responseHeaders = new HttpHeaders();


    @RequestMapping(value = "/get/{name}", method = RequestMethod.GET, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object getByName(@PathVariable("name") String name) {

        String result = null;
        try {
            result = new ObjectMapper().writeValueAsString(ingredientService.getByName(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object addIngredient(@RequestBody String name) {

        ingredientService.addIngredient(name);
        return new ResponseEntity<>("{\"ingredient\":\"" + name + "\"}", responseHeaders, HttpStatus.OK);

    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object getAllIngredients() {

        String result = null;
        try {
            result = new ObjectMapper().writeValueAsString(ingredientService.getAllIngredients());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}