package com.project.classroom.persistence;

import com.project.classroom.model.GradeLevel;
import com.project.classroom.persistence.impl.Dao;

/**
 * Interface for data access operations on the {@link GradeLevel} entity.
 * Extends the generic Dao interface for CRUD operations and includes additional methods
 * specific to the {@link GradeLevel} entity.
 */
public interface GradeLevelDao extends Dao<GradeLevel> {

  /**
   * Finds a {@link GradeLevel} entity by its name.
   *
   * @param name the name of the grade level to find.
   * @return the {@link GradeLevel} entity if found, otherwise {@code null}.
   */
  GradeLevel findByName(String name);
}
