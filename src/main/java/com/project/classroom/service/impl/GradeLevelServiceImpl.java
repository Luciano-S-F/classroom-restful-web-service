package com.project.classroom.service.impl;

import com.project.classroom.model.GradeLevel;
import com.project.classroom.persistence.GradeLevelDao;
import com.project.classroom.service.GradeLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Service layer for managing grade levels, handling business logic with transactional support.
 * This class uses transactional annotations to ensure that database operations are handled
 * within the context of a transaction, maintaining data integrity and consistency.
 */
@Service
public class GradeLevelServiceImpl implements GradeLevelService {

  private GradeLevelDao gradeLevelDao;

  @Autowired
  public void setGradeLevelDao(GradeLevelDao gradeLevelDao) {
    this.gradeLevelDao = gradeLevelDao;
  }

  /**
   * Adds a new grade level to the database.
   * @param gradeLevel The grade level entity to be persisted.
   */
  @Override
  @Transactional
  public void add(GradeLevel gradeLevel) {
    gradeLevelDao.create(gradeLevel);
  }

  /**
   * Deletes an existing grade level from the database.
   * @param gradeLevel The grade level entity to be removed.
   */
  @Override
  @Transactional
  public void delete(GradeLevel gradeLevel) {
    GradeLevel managedGradeLevel = gradeLevelDao.readById(gradeLevel.getId());
    if (managedGradeLevel != null) {
      gradeLevelDao.delete(managedGradeLevel);
    }
  }

  /**
   * Finds a grade level by its identifier.
   * @param id The identifier of the grade level.
   * @return The found grade level or null if no grade level is found.
   */
  @Override
  @Transactional
  public GradeLevel findById(Integer id) {
    return gradeLevelDao.readById(id);
  }

  /**
   * Updates an existing grade level in the database.
   * @param gradeLevel The grade level entity with updated information to be persisted.
   * @return The updated grade level.
   */
  @Override
  @Transactional
  public GradeLevel update(GradeLevel gradeLevel) {
    return gradeLevelDao.update(gradeLevel);
  }

  /**
   * Retrieves all grade levels from the database.
   * @return A list of all grade levels.
   */
  @Override
  @Transactional
  public List<GradeLevel> list() {
    return gradeLevelDao.list();
  }

  /**
   * Finds a grade level by its name.
   * @param name The name of the grade level.
   * @return The grade level with the specified name or null if it does not exist.
   */
  @Override
  @Transactional
  public GradeLevel findByName(String name) {
    return gradeLevelDao.findByName(name);
  }
}
