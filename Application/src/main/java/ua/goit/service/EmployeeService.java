package ua.goit.service;

import org.springframework.stereotype.Service;
import ua.goit.domain.Cook;
import ua.goit.domain.Employee;
import ua.goit.domain.Waiter;

import java.util.List;

/**
 * Created by user on 23.05.2017.
 */
@Service
public interface EmployeeService {

    Employee addEmployee(Employee employee);
    Waiter addWaiter(Waiter waiter);
    Cook addCook(Cook cook);
    void deleteEmployee(String name);
    Employee getByName(String name);
    List<Employee> getAllEmployees();
}
