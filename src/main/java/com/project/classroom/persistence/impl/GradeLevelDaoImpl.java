package com.project.classroom.persistence.impl;

import com.project.classroom.model.GradeLevel;
import com.project.classroom.persistence.AbstractDao;
import com.project.classroom.persistence.GradeLevelDao;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Implementation of the GradeLevelDao interface to provide CRUD operations for {@link GradeLevel} entities.
 */
@Repository
public class GradeLevelDaoImpl extends AbstractDao<GradeLevel> implements GradeLevelDao {

  /**
   * Retrieves a {@link GradeLevel} entity by its identifier.
   *
   * @param id The identifier of the grade level to find.
   * @return The found GradeLevel or null if no grade level is found with the provided ID.
   */
  @Override
  public GradeLevel readById(Integer id) {
    return getEntityManager().find(GradeLevel.class, id);
  }

  /**
   * Finds a grade level by its name.
   *
   * @param name The name of the grade level to find.
   * @return The found GradeLevel or null if no grade level exists with the provided name.
   */
  @Override
  public GradeLevel findByName(String name) {
    TypedQuery<GradeLevel> query = getEntityManager().createQuery(
            "SELECT g FROM GradeLevel g WHERE g.name = :nameParam", GradeLevel.class);
    query.setParameter("nameParam", name);

    try {
      return query.getSingleResult();
    } catch (NoResultException ex) {
      return null;
    }
  }

  /**
   * Lists all grade levels stored in the database.
   *
   * @return A list of all GradeLevel entities.
   */
  @Override
  public List<GradeLevel> list() {
    TypedQuery<GradeLevel> query = getEntityManager().createQuery(
            "SELECT g FROM GradeLevel g", GradeLevel.class);
    return query.getResultList();
  }
}
