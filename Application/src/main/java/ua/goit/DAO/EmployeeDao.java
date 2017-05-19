package ua.goit.DAO;

import ua.goit.domain.Cook;
import ua.goit.domain.Employee;
import ua.goit.domain.Waiter;

import java.util.List;


public interface EmployeeDao {

    void addEmployee(Employee employee);

    void addWaiter(Waiter waiter);

    void addCook(Cook cook);

    void removeEmployee(String name);

    Employee findEmployeeByName(String name);

    List<Employee> getAllEmployees();
}