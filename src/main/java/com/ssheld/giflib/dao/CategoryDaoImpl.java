package com.ssheld.giflib.dao;

import com.ssheld.giflib.model.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

/**
 * Author: Stephen Sheldon 3/30/2019
 */
@Repository
public class CategoryDaoImpl implements CategoryDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Category> findAll() {
        // Open a session
        Session session = sessionFactory.openSession();

        // Create CriteriaBuilder
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create CriteriaQuery
        CriteriaQuery<Category> criteria = builder.createQuery(Category.class);

        // Specify criteria root
        criteria.from(Category.class);

        // Execute query
        List<Category> categories = session.createQuery(criteria).getResultList();

        // Close session
        session.close();

        return categories;
    }

    @Override
    public Category findById(Long id) {
        Session session = sessionFactory.openSession();
        Category category = session.get(Category.class, id);
        session.close();
        return category;
    }

    @Override
    public void save(Category category) {
        // Open a session
        Session session = sessionFactory.openSession();

        // Begin a transaction
        session.beginTransaction();

        // Save or update the category
        session.saveOrUpdate(category);

        // Commit the transaction
        session.getTransaction().commit();

        // Close the session
        session.close();
    }

    @Override
    public void delete(Category category) {

    }
}
