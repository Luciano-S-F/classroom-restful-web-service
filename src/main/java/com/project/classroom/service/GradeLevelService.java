package com.project.classroom.service;

import com.project.classroom.model.GradeLevel;

/**
 * Interface for service operations on grade levels.
 * Extends from the generic Services interface, adding functionalities specific to grade levels.
 */
public interface GradeLevelService extends Services<GradeLevel> {

  /**
   * Retrieves a grade level by its name.
   *
   * @param name The name of the grade level to find.
   * @return The grade level object if found; null otherwise.
   */
  GradeLevel findByName(String name);
}
