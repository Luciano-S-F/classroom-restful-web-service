package com.project.classroom.persistence;

import com.project.classroom.model.AbstractModel;
import com.project.classroom.persistence.impl.Dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Generic DAO implementation providing basic CRUD operations for entities extending {@link AbstractModel}.
 * This class is designed to be extended by other DAO implementation classes for specific entity types.
 *
 * @param <T> The type of the entity this DAO manages, which must extend {@link AbstractModel}.
 */
public abstract class AbstractDao<T extends AbstractModel> implements Dao<T> {

  @PersistenceContext
  private EntityManager entityManager;

  private Class<T> tClass;

  public EntityManager getEntityManager() {
    return entityManager;
  }

  public void setEntityManager(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public Class<T> gettClass() {
    return tClass;
  }

  public void settClass(Class<T> tClass) {
    this.tClass = tClass;
  }

  @Override
  public void create(T entity) {
    entityManager.persist(entity);
  }

  @Override
  public T update(T entity) {
    return entityManager.merge(entity);
  }

  @Override
  public T readById(Integer id) {
    return entityManager.find(tClass, id);
  }

  @Override
  public void delete(T entity) {
    if (!entityManager.contains(entity)) {
      entity = entityManager.merge(entity);
    }
    entityManager.remove(entity);
  }

  @Override
  public List<T> list() {
    TypedQuery<T> typedQuery = entityManager.createQuery("SELECT a FROM " + tClass.getName() + " a", tClass);
    return typedQuery.getResultList();
  }
}
