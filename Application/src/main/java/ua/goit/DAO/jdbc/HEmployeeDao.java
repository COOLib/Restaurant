package ua.goit.DAO.jdbc;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.domain.Cook;
import ua.goit.domain.Employee;
import ua.goit.domain.Waiter;
import ua.goit.DAO.EmployeeDao;

import java.util.List;

@Component
public class HEmployeeDao implements EmployeeDao {

    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void addEmployee(Employee employee) {
        sessionFactory.getCurrentSession().save(employee);
    }

    @Override
    @Transactional
    public void addWaiter(Waiter waiter) {
        sessionFactory.getCurrentSession().save(waiter);

    }

    @Override
    @Transactional
    public void addCook(Cook cook) {
        sessionFactory.getCurrentSession().save(cook);

    }

    @Override
    @Transactional
    public void removeEmployee(String name) {

        Employee employee = findEmployeeByName(name);
        Session session = sessionFactory.getCurrentSession();

        session.delete(employee);
    }

    @Override
    @Transactional
    public Employee findEmployeeByName(String name) {

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(Employee.class)
                .add(Restrictions.like("name", name));

        Employee employee = (Employee) criteria.uniqueResult();

        if (employee == null) {
            throw new RuntimeException("Cannot find employee with name" + name);
        }

        return employee;
    }

    @Override
    @Transactional
    public List<Employee> getAllEmployees() {

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Employee> employees = session.createCriteria(Employee.class).list();

        return employees;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}