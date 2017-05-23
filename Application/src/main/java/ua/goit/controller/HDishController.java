package ua.goit.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ua.goit.domain.Dish;
import ua.goit.service.DishService;
import ua.goit.service.impl.DishServiceImpl;

import java.io.IOException;

@RestController
@RequestMapping(value = "/restaurant")
public class HDishController {

    @Autowired
    private DishServiceImpl dishService;
    private static HttpHeaders responseHeaders = new HttpHeaders();

    @RequestMapping(value = "/addDish", method = RequestMethod.PUT, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object addDish(Dish dish) {

        dishService.addDish(dish);

        return new ResponseEntity<>("{\"id_order\":" + dish.getName() + "}", responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteDish", method = RequestMethod.DELETE, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object deleteDish(String name) {

        dishService.deleteDish(name);

        return new ResponseEntity<>("{\"id_order\":" + name + "}", responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/getDishByName", method = RequestMethod.GET, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object getByName(String name) {

        String result = null;
        try {
            result = new ObjectMapper().writeValueAsString(dishService.getByName(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/getAllDishes", method = RequestMethod.GET, headers = {"Content-Type=application/json"},
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