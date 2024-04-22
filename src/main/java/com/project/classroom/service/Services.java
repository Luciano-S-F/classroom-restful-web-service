package com.project.classroom.service;

import com.project.classroom.model.AbstractModel;

import java.util.List;

/**
 * Generic interface for common service operations across all entities that extend AbstractModel.
 * This interface defines the basic CRUD operations to manage database entities.
 *
 * @param <T> Type parameter that extends AbstractModel, indicating the entity type managed by this service.
 */
public interface Services<T extends AbstractModel> {

  /**
   * Adds a new entity to the persistence layer.
   *
   * @param ele The entity to be added.
   */
  void add(T ele);

  /**
   * Deletes an existing entity from the persistence layer.
   *
   * @param ele The entity to be deleted.
   */
  void delete(T ele);

  /**
   * Finds an entity by its ID.
   *
   * @param id The unique identifier of the entity.
   * @return The found entity, or null if no entity is found.
   */
  T findById(Integer id);

  /**
   * Updates an existing entity in the persistence layer.
   *
   * @param ele The entity to update.
   * @return The updated entity.
   */
  T update(T ele);

  /**
   * Retrieves a list of all entities of type T from the persistence layer.
   *
   * @return A list of entities.
   */
  List<T> list();
}
