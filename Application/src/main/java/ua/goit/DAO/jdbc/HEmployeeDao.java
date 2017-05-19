package ua.goit.DAO.jdbc;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.domain.Cook;
import ua.goit.domain.Employee;
import ua.goit.domain.Waiter;
import ua.goit.DAO.EmployeeDao;

import java.util.List;

public class HEmployeeDao implements EmployeeDao {

    private SessionFactory sessionFactory;
    private static final Logger LOGGER = LoggerFactory.getLogger(HEmployeeDao.class);

    @Override
    @Transactional
    public void addEmployee(Employee employee) {

        LOGGER.info("Connecting to database. Running method is addEmployee");
        sessionFactory.getCurrentSession().save(employee);
    }

    @Override
    @Transactional
    public void addWaiter(Waiter waiter) {
        LOGGER.info("Connecting to database. Running method is addWaiter");
        sessionFactory.getCurrentSession().save(waiter);

    }

    @Override
    @Transactional
    public void addCook(Cook cook) {
        LOGGER.info("Connecting to database. Running method is addCook");
        sessionFactory.getCurrentSession().save(cook);

    }

    @Override
    @Transactional
    public void removeEmployee(String name) {

        LOGGER.info("Connecting to database. Running method is removeEmployee");

        Employee employee = findEmployeeByName(name);
        Session session = sessionFactory.getCurrentSession();

        session.delete(employee);
    }

    @Override
    @Transactional
    public Employee findEmployeeByName(String name) {

        LOGGER.info("Connecting to database. Running method is findEmployeeByName");

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(Employee.class)
                .add(Restrictions.like("name", name));

        Employee employee = (Employee) criteria.uniqueResult();

        if (employee == null) {
            LOGGER.error("Cannot find employee with name" + name);
            throw new RuntimeException("Cannot find employee with name" + name);
        }

        return employee;
    }

    @Override
    @Transactional
    public List<Employee> getAllEmployees() {

        LOGGER.info("Connecting to database. Running method is getAllEmployees");

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Employee> employees = session.createCriteria(Employee.class).list();

        return employees;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}