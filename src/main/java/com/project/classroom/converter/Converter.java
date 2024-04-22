package com.project.classroom.converter;

/**
 * This interface defines a generic conversion logic to convert objects of one type into another.
 * It provides a method for converting an object of type T to type U.
 *
 * @param <T> the type of object to convert from
 * @param <U> the type of object to convert to
 */
public interface Converter<T, U> {

    /**
     * Converts an object of type T to type U.
     *
     * @param element the object to convert
     * @return the converted object of type U
     */
    U convert(T element);
}
