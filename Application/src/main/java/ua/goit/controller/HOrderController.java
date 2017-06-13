package ua.goit.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ua.goit.domain.Dish;
import ua.goit.domain.Order;
import ua.goit.domain.Waiter;
import ua.goit.DAO.DishDao;
import ua.goit.DAO.EmployeeDao;
import ua.goit.DAO.OrderDao;
import ua.goit.service.OrderService;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/restaurant/order")
public class HOrderController {

    @Autowired
    private OrderService orderService;
    private static HttpHeaders responseHeaders = new HttpHeaders();


    @RequestMapping(value = "/add", method = RequestMethod.PUT, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object addOrder(@RequestBody Order order) {

        order.setDate(new Date());
        order.setClosed("opened");

        orderService.addOrder(order);
        return new ResponseEntity<>("{\"order\":\"" + order.getNumber() + "\"}", responseHeaders, HttpStatus.OK);

    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object deleteDish(@PathVariable("id") Integer id) {

        orderService.deleteOrder(id);
        return new ResponseEntity<>("{\"deleted\":\"" + id + "\"}", responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/turnToClosed/{id}", method = RequestMethod.PUT, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object turnToClosed(@PathVariable("id") int id) {

       orderService.turnToClosed(id);
       return new ResponseEntity<>("{\"closed\":\"" + id + "\"}", responseHeaders, HttpStatus.OK);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object getById(@PathVariable("id") int id) {

        String result = null;
        try {
            result = new ObjectMapper().writeValueAsString(orderService.getById(id));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/getAllClosed", method = RequestMethod.GET, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object getAllClosed() {

        String result = null;
        try {
            result = new ObjectMapper().writeValueAsString(orderService.getAllClosed());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/getAllOpened", method = RequestMethod.GET, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object getAllOpened() {

        String result = null;
        try {
            result = new ObjectMapper().writeValueAsString(orderService.getAllOpened());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping(value = "/addDishToOrder/{orderId}/{dishName}", method = RequestMethod.PUT, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object addDishToOrder(@PathVariable("orderId") int orderId, @PathVariable("dishName") String dishName) {

        orderService.addDishToOrder(orderId, dishName);
        return new ResponseEntity<>("{\"order\":\"" + orderId + "\",\"dish\":\"" + dishName + "\"}", responseHeaders, HttpStatus.OK);

    }

    @RequestMapping(value = "/deleteDishFromOrder/{orderId}/{dishName}", method = RequestMethod.DELETE, headers = {"Content-Type=application/json"},
            produces = {"application/json; charset=UTF-8"})
    public
    @ResponseBody
    Object deleteDishFromOrder(@PathVariable("orderId") int orderId, @PathVariable("dishName") String dishName) {

        orderService.deleteDishFromOrder(orderId, dishName);
        return new ResponseEntity<>("{\"order\":\"" + orderId + "\",\"dish\":\"" + dishName + "\"}", responseHeaders, HttpStatus.OK);
    }
}