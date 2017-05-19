package ua.goit.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.goit.DAO.*;
import ua.goit.domain.*;
import ua.goit.service.CookedDishService;
import ua.goit.service.CookedDishServiceInterface;

import java.util.Date;
import java.util.List;

public class HCookedDishController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HCookedDishController.class);
    private static HttpHeaders responseHeaders = new HttpHeaders();

    @Autowired
    private CookedDishServiceInterface cookedDishService;

    @RequestMapping(value = "/cloudPay", method = RequestMethod.PUT, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object addCookedDish(@RequestBody CookedDish cookedDish) {


        return new ResponseEntity<>(responseHeaders, HttpStatus.OK);
    }


    public List<CookedDish> getAllCookedDishes() {

        return cookedDishService.getAllCookedDishes();
    }
}