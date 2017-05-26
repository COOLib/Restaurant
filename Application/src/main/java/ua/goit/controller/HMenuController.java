package ua.goit.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ua.goit.domain.Dish;
import ua.goit.domain.Menu;
import ua.goit.DAO.DishDao;
import ua.goit.DAO.MenuDao;
import ua.goit.service.DishService;
import ua.goit.service.MenuService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/restaurant/menu")
public class HMenuController {


    @Autowired
    private MenuService menuService;

    private static HttpHeaders responseHeaders = new HttpHeaders();

    @RequestMapping(value = "/getAll", method = RequestMethod.GET, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object getAllMenus() {

        String result = null;
        try {
            result = new ObjectMapper().writeValueAsString(menuService.getAllMenus());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object addMenu(@RequestBody Menu menu) {

        menuService.addMenu(menu);
        return new ResponseEntity<>("{\"menu\":\"" + menu.getName() + "\"}", responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{name}", method = RequestMethod.DELETE, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object deleteMenu(@PathVariable("name") String name) {

        menuService.deleteMenu(name);
        return new ResponseEntity<>("{\"deleted\":\"" + name + "\"}", responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{name}", method = RequestMethod.GET, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object getByName(@PathVariable("name") String name) {

        String result = null;
        try {
            result = new ObjectMapper().writeValueAsString(menuService.getByName(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/addToMenu/{menuName}/{dishName}", method = RequestMethod.PUT, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object addDishToMenu(@PathVariable("menuName") String menuName, @PathVariable("dishName") String dishName) {

        menuService.addDishToMenu(menuName, dishName);
        return new ResponseEntity<>("{\"menu\":\"" + menuName + "\",\"dish\":\"" + dishName + "\"}", responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteFromMenu/{menuName}/{dishName}", method = RequestMethod.DELETE, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object deleteDishFromMenu(@PathVariable("menuName") String menuName, @PathVariable("dishName") String dishName) {

        menuService.deleteDishFromMenu(menuName, dishName);
        return new ResponseEntity<>("{\"deleted\":\"" + dishName + "\",menu\":\"" + menuName + "\"}", responseHeaders, HttpStatus.OK);

    }
}