package com.project.classroom.service;

import com.project.classroom.model.Learner;

/**
 * Interface for service operations on learners.
 * Extends from the generic Services interface, adding specific functionality to handle learner entities.
 */
public interface LearnerService extends Services<Learner> {

  /**
   * Deletes a learner by their unique identifier.
   * This method ensures that the learner is removed from the persistence context.
   *
   * @param id The unique identifier of the learner to be deleted.
   */
  void deleteById(Integer id);
}
