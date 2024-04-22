package com.project.classroom.persistence.impl;

import com.project.classroom.model.Learner;
import com.project.classroom.persistence.AbstractDao;
import com.project.classroom.persistence.LearnerDao;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Implementation of the LearnerDao interface to provide CRUD operations for {@link Learner} entities.
 */
@Repository
public class LearnerDaoImpl extends AbstractDao<Learner> implements LearnerDao {

    public LearnerDaoImpl() {
        settClass(Learner.class);
    }

    /**
     * Deletes a {@link Learner} entity based on its identifier.
     *
     * @param id The identifier of the learner to delete.
     */
    @Override
    public void deleteById(Integer id) {
        Query query = getEntityManager().createQuery("DELETE FROM Learner l WHERE l.id = :idParam");
        query.setParameter("idParam", id);
        query.executeUpdate();
    }

    /**
     * Retrieves a {@link Learner} entity by its identifier using an enhanced readById method that ensures
     * the entity type is set correctly.
     *
     * @param id The identifier of the learner to find.
     * @return The found Learner or null if no learner is found with the provided ID.
     */
    @Override
    public Learner readById(Integer id) {
        settClass(Learner.class);
        return super.readById(id);
    }

    /**
     * Lists all learners stored in the database.
     *
     * @return A list of all Learner entities.
     */
//    @Override
//    public List<Learner> list() {
//        TypedQuery<Learner> typedQuery = getEntityManager().createQuery("SELECT l FROM Learner l", Learner.class);
//        return typedQuery.getResultList();
//    }
}
