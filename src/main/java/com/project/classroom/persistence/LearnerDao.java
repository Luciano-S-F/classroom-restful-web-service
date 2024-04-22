package com.project.classroom.persistence;

import com.project.classroom.model.Learner;
import com.project.classroom.persistence.impl.Dao;

/**
 * Interface for data access operations specific to the {@link Learner} entity.
 * This extends the generic {@link Dao} interface to include CRUD operations tailored for {@link Learner} objects,
 * and provides additional functionality for deleting a learner by ID.
 */
public interface LearnerDao extends Dao<Learner> {

  /**
   * Deletes a {@link Learner} entity using its ID. This method is intended to handle the removal of a learner
   * from the database cleanly, ensuring all references and constraints are appropriately managed.
   *
   * @param id the unique identifier of the learner to delete.
   */
  void deleteById(Integer id);
}
