package com.project.classroom.persistence.impl;

import com.project.classroom.model.AbstractModel;

import java.util.List;

/**
 * This interface defines the standard operations to be performed on a model object(s)
 * as part of the Data Access Object (DAO) pattern.
 *
 * @param <T> the type of the entity model that extends {@link AbstractModel}
 */
public interface Dao<T extends AbstractModel> {

  /**
   * Creates a new entity in the database.
   *
   * @param ele the entity to be created
   */
  void create(T ele);

  /**
   * Updates an existing entity in the database.
   *
   * @param ele the entity to be updated
   * @return the updated entity
   */
  T update(T ele);

  /**
   * Reads an entity by its identifier.
   *
   * @param id the identifier of the entity to be retrieved
   * @return the entity found, or null if no entity is found with the provided identifier
   */
  T readById(Integer id);

  /**
   * Deletes an entity from the database.
   *
   * @param ele the entity to be deleted
   */
  void delete(T ele);

  /**
   * Lists all entities from the database.
   *
   * @return a list of entities
   */
  List<T> list();
}
