package ua.goit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ua.goit.domain.*;
import ua.goit.DAO.EmployeeDao;
import ua.goit.service.EmployeeService;
import ua.goit.service.impl.EmployeeServiceImpl;

import java.util.Date;
import java.util.List;

@RestController
public class HEmployeeController {

    private EmployeeDao employeeDao;
    @Autowired
    private EmployeeService employeeService;


    public void addEmployee(String name, String surname, Date birth, String phone, int salary) {

        Employee employee = new Employee();

        employee.setName(name);
        employee.setSecondName(surname);
        employee.setBirthDate(birth);
        employee.setPhone(phone);
        employee.setSalary(salary);

        employeeDao.addEmployee(employee);
    }

    public void addWaiter(String name, String surname, Date birth, String phone, int salary, Position position) {

        Waiter waiter = new Waiter();

        waiter.setName(name);
        waiter.setSecondName(surname);
        waiter.setBirthDate(birth);
        waiter.setPhone(phone);
        waiter.setSalary(salary);
        waiter.setPosition(position);

        employeeDao.addWaiter(waiter);
    }

    public void addCook(String name, String surname, Date birth, String phone, int salary, Position position) {

        Cook cook = new Cook();

        cook.setName(name);
        cook.setSecondName(surname);
        cook.setBirthDate(birth);
        cook.setPhone(phone);
        cook.setSalary(salary);
        cook.setPosition(position);

        employeeDao.addCook(cook);
    }

    public void deleteEmployee(String name) {

        employeeDao.removeEmployee(name);
    }

    public Employee getByName(String name) {

        return employeeDao.findEmployeeByName(name);
    }

    public List<Employee> getAllEmployees() {

        return employeeDao.getAllEmployees();
    }

    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }
}