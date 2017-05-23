package ua.goit.DAO.jdbc;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.domain.Ingredient;
import ua.goit.DAO.IngredientDao;

import java.util.List;

@Component
public class HIngredientDao implements IngredientDao {

    private SessionFactory sessionFactory;
    private static final Logger LOGGER = LoggerFactory.getLogger(HIngredientDao.class);

    @Override
    @Transactional
    public Ingredient findIngredientByName(String name) {

        LOGGER.info("Connecting to database. Running method is findIngredientByName");

        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(Ingredient.class)
                .add(Restrictions.like("name", name));

        Ingredient ingredient = (Ingredient) criteria.uniqueResult();

        if (ingredient == null) {
            LOGGER.error("Cannot find ingredient with name " + name);
            throw new RuntimeException("Cannot find ingredient with name " + name);
        }
        return ingredient;
    }

    @Override
    @Transactional
    public void addIngredient(Ingredient ingredient) {

        LOGGER.info("Connecting to database. Running method is addIngredient");
        sessionFactory.getCurrentSession().save(ingredient);
    }

    @Override
    @Transactional
    public List<Ingredient> allIngredients() {

        LOGGER.info("Connecting to database. Running method is allIngredients");

        Session session = sessionFactory.getCurrentSession();
        List<Ingredient> ingredients = session.createCriteria(Ingredient.class).list();
        return ingredients;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}