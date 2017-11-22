package ua.goit.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.goit.domain.*;
import ua.goit.service.CookedDishService;

@RestController
@RequestMapping(value = "/restaurant/cookedDishes")
public class HCookedDishController {

    private static HttpHeaders responseHeaders = new HttpHeaders();

    @Autowired
    private CookedDishService cookedDishService;

    @RequestMapping(value = "/add", method = RequestMethod.PUT, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object addCookedDish(@RequestBody CookedDish cookedDish) {

        return new ResponseEntity<>("{\"id_cookedDish\":" + cookedDish.getId() + "}",responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object getAllCookedDishes() {

        return cookedDishService.getAllCookedDishes();
    }
}