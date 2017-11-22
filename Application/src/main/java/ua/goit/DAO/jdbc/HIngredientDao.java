package ua.goit.DAO.jdbc;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.domain.Ingredient;
import ua.goit.DAO.IngredientDao;

import java.util.List;

@Component
public class HIngredientDao implements IngredientDao {

    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public Ingredient findIngredientByName(String name) {

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(Ingredient.class)
                .add(Restrictions.like("name", name));

        Ingredient ingredient = (Ingredient) criteria.uniqueResult();

        if (ingredient == null) {
            throw new RuntimeException("Cannot find ingredient with name " + name);
        }
        return ingredient;
    }

    @Override
    @Transactional
    public void addIngredient(Ingredient ingredient) {
        sessionFactory.getCurrentSession().save(ingredient);
    }

    @Override
    @Transactional
    public List<Ingredient> allIngredients() {

        Session session = sessionFactory.getCurrentSession();
        List<Ingredient> ingredients = session.createCriteria(Ingredient.class).list();
        return ingredients;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}