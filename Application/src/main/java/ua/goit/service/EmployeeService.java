package ua.goit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.domain.Cook;
import ua.goit.domain.Employee;
import ua.goit.domain.Position;
import ua.goit.domain.Waiter;
import ua.goit.DAO.EmployeeDao;

import java.util.Date;
import java.util.List;

/**
 * Created by user on 08.11.2016.
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Transactional
    public Employee addEmployee(String name, String surname, Date birth, String phone, int salary) {

        Employee employee = new Employee();

        employee.setName(name);
        employee.setSecondName(surname);
        employee.setBirthDate(birth);
        employee.setPhone(phone);
        employee.setSalary(salary);

        employeeDao.addEmployee(employee);

        return employee;
    }

    @Transactional
    public Waiter addWaiter(String name, String surname, Date birth, String phone, int salary, Position position) {

        Waiter waiter = new Waiter();

        waiter.setName(name);
        waiter.setSecondName(surname);
        waiter.setBirthDate(birth);
        waiter.setPhone(phone);
        waiter.setSalary(salary);
        waiter.setPosition(position);

        employeeDao.addWaiter(waiter);

        return waiter;
    }

    @Transactional
    public Cook addCook(String name, String surname, Date birth, String phone, int salary, Position position) {

        Cook cook = new Cook();

        cook.setName(name);
        cook.setSecondName(surname);
        cook.setBirthDate(birth);
        cook.setPhone(phone);
        cook.setSalary(salary);
        cook.setPosition(position);

        employeeDao.addCook(cook);

        return cook;
    }

    @Transactional
    public void deleteEmployee(String name) {

        employeeDao.removeEmployee(name);
    }

    @Transactional
    public Employee getByName(String name) {

        return employeeDao.findEmployeeByName(name);
    }

    @Transactional
    public List<Employee> getAllEmployees() {

        return employeeDao.getAllEmployees();
    }
}
