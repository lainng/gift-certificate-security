package com.piatnitsa.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;

import java.util.List;

/**
 * This interface describes filtering operations for working with database tables.
 * @param <T> type of entity.
 *
 * @author Vlad Piatnitsa
 * @version 1.0
 */
public interface FilteringRepository<T> {

    /**
     * Method for getting a list of {@link T} by specific parameters.
     * @param params request parameters from URL.
     * @return {@link List} of {@link T}.
     */
    List<T> findWithFilter(MultiValueMap<String, String> params, Pageable pageable);
}
