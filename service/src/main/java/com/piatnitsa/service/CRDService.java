package com.piatnitsa.service;

import org.springframework.util.MultiValueMap;

import java.util.List;

/**
 * This interface describes CRD operations for working with objects.
 * @param <T> type of entity.
 * @author Vlad Piatnitsa
 * @version 1.0
 */
public interface CRDService<T> {

    /**
     * Retrieves a {@link T} object by its ID.
     * @param id An ID of the object.
     * @return A {@link T} object.
     */
    T getById(long id);

    /**
     * Retrieves a {@link List} of {@link T} objects.
     * @return A {@link List} of {@link T} objects.
     */
    List<T> getAll(int page, int size);

    /**
     * Method for saving an {@link T} entity
     * @param entity an {@link T} entity to save.
     */
    T insert(T entity);

    /**
     * Removes an {@link T} entity from data source by its ID.
     * @param id an ID of {@link T} entity.
     */
    void removeById(long id);

    /**
     * Method for getting a list of {@link T} by specific parameters.
     * @param page page index.
     * @param size the size of the page to be returned.
     * @return {@link List} of {@link T}.
     */
    List<T> doFilter(MultiValueMap<String, String> params, int page, int size);
}
