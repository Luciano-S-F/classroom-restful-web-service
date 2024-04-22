package com.project.classroom.model;

/**
 * Provides a common interface for all entity models in the application.
 * This interface ensures that all entities will have a standard way to handle their primary key.
 */
public interface Model {

    /**
     * Gets the unique identifier of the entity.
     *
     * @return the unique identifier
     */
    Integer getId();

    /**
     * Sets the unique identifier of the entity.
     *
     * @param id the unique identifier to set
     */
    void setId(Integer id);

}
