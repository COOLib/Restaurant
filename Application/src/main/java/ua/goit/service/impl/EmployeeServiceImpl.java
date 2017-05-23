package ua.goit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.domain.Cook;
import ua.goit.domain.Employee;
import ua.goit.domain.Position;
import ua.goit.domain.Waiter;
import ua.goit.DAO.EmployeeDao;
import ua.goit.service.EmployeeService;

import java.util.Date;
import java.util.List;

/**
 * Created by user on 08.11.2016.
 */
@Service
public class EmployeeServiceImpl  implements EmployeeService{

    @Autowired
    private EmployeeDao employeeDao;

    @Transactional
    public Employee addEmployee(Employee employee) {

        employeeDao.addEmployee(employee);

        return employee;
    }

    @Transactional
    public Waiter addWaiter(Waiter waiter) {

        employeeDao.addWaiter(waiter);

        return waiter;
    }

    @Transactional
    public Cook addCook(Cook cook) {

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
