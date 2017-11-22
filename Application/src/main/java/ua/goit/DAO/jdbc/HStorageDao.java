package ua.goit.DAO.jdbc;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.domain.Ingredient;
import ua.goit.domain.Storage;
import ua.goit.DAO.StorageDao;

import java.util.List;

@Component
public class HStorageDao implements StorageDao {

    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void addIngredientToStorage(Ingredient ingredient, int quantity) {

        Storage storage = new Storage();

        storage.setIngredient(ingredient);
        storage.setQuantity(quantity);
        sessionFactory.getCurrentSession().save(storage);
    }

    @Override
    @Transactional
    public void removeIngredientFromStorage(String name) {

        Storage storage = findIngredientByName(name);

        int quantity = storage.getQuantity() * (-1);
        updateQuantity(name, quantity);
    }

    @Override
    @Transactional
    public List getAllIngredients() {

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        return session.createCriteria(Storage.class).list();
    }

    @Override
    @Transactional
    public List<Storage> getAllEndingIngredients() {

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        return session.createCriteria(Storage.class)
                .add(Restrictions.le("quantity", 10))
                .list();
    }

    @Override
    @Transactional
    public Storage findIngredientByName(String name) {

        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select e from Storage e where e.id=" +
                "(select o.id from Ingredient o where o.name=:name)");
        query.setParameter("name", name);

        Storage storage = (Storage) query.uniqueResult();

        if (storage == null) {
            throw new RuntimeException("Cannot find ingredient with name " + name + " at storage");

        }

        return storage;
    }

    @Override
    @Transactional
    public void updateQuantity(String ingredientName, int quantity) {

        Storage storage = findIngredientByName(ingredientName);
        int realQuantity = storage.getQuantity();

        if (realQuantity + quantity >= 0) {
            storage.setQuantity(realQuantity + quantity);
        } else {

            throw new RuntimeException("Cannot update quantity at storage, because it will be less than zero.");
        }
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}