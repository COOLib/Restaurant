package ua.goit.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.goit.domain.Dish;
import ua.goit.service.DishService;
import ua.goit.service.impl.DishServiceImpl;

import java.io.IOException;

@RestController
@RequestMapping(value = "/restaurant/dish")
public class HDishController {

    @Autowired
    private DishService dishService;
    private static HttpHeaders responseHeaders = new HttpHeaders();

    @RequestMapping(value = "/add", method = RequestMethod.PUT, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object addDish(@RequestBody Dish dish) {

        dishService.addDish(dish);
        return new ResponseEntity<>("{\"dish\":\"" + dish.getName() + "\"}", responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{name}", method = RequestMethod.DELETE, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object deleteDish(@PathVariable("name") String name) {

        dishService.deleteDish(name);
        return new ResponseEntity<>("{\"deleted\":\"" + name + "\"}", responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{name}", method = RequestMethod.GET, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object getByName(@PathVariable("name") String name) {

        String result = null;
        try {
            result = new ObjectMapper().writeValueAsString(dishService.getByName(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object getAllDishes() {

        String result = null;
        try {
            result = new ObjectMapper().writeValueAsString(dishService.getAllDishes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}