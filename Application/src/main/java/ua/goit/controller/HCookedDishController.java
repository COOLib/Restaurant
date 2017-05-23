package ua.goit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.goit.domain.*;
import ua.goit.service.CookedDishService;

@RestController
@RequestMapping(value = "/restaurant")
public class HCookedDishController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HCookedDishController.class);
    private static HttpHeaders responseHeaders = new HttpHeaders();

    @Autowired
    private CookedDishService cookedDishService;

    @RequestMapping(value = "/addCookedDish", method = RequestMethod.PUT, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object addCookedDish(@RequestBody CookedDish cookedDish) {

        return new ResponseEntity<>("{\"id_cookedDish\":" + cookedDish.getId() + "}",responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllCookedDishes", method = RequestMethod.GET, produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object getAllCookedDishes() {

        return cookedDishService.getAllCookedDishes();
    }
}