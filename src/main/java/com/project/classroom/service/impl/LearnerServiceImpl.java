package com.project.classroom.service.impl;

import com.project.classroom.persistence.LearnerDao;
import com.project.classroom.model.Learner;
import com.project.classroom.service.LearnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Service layer for managing learners, handling business logic with transactional support.
 * Each method in this class is annotated with {@link Transactional}, ensuring that the database operations are executed
 * within a transaction context, which helps maintain data consistency and integrity.
 */
@Service
public class LearnerServiceImpl implements LearnerService {

  private LearnerDao learnerDao;

  @Autowired
  public void setLearnerDao(LearnerDao learnerDao) {
    this.learnerDao = learnerDao;
  }

  /**
   * Adds a new learner to the database.
   * @param learner The learner entity to be persisted.
   */
  @Override
  @Transactional
  public void add(Learner learner) {
    learnerDao.create(learner);
  }

  /**
   * Deletes an existing learner from the database.
   * @param learner The learner entity to be removed.
   */
  @Override
  @Transactional
  public void delete(Learner learner) {
    Learner managedLearner = learnerDao.readById(learner.getId());
    if (managedLearner != null) {
      learnerDao.delete(managedLearner);
    }
  }

  /**
   * Finds a learner by their identifier.
   * @param id The identifier of the learner.
   * @return The found learner, or null if no learner is found with the given ID.
   */
  @Override
  @Transactional
  public Learner findById(Integer id) {
    return learnerDao.readById(id);
  }

  /**
   * Updates an existing learner in the database.
   * @param learner The learner entity with updated information to be persisted.
   * @return The updated learner.
   */
  @Override
  @Transactional
  public Learner update(Learner learner) {
    return learnerDao.update(learner);
  }

  /**
   * Retrieves all learners from the database.
   * @return A list of all learners.
   */
  @Override
  @Transactional
  public List<Learner> list() {
    return learnerDao.list();
  }

  /**
   * Deletes a learner from the database by their ID.
   * @param id The identifier of the learner to be removed.
   */
  @Override
  @Transactional
  public void deleteById(Integer id) {
    learnerDao.deleteById(id);
  }
}
