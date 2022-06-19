package com.piatnitsa.dao;

import com.piatnitsa.entity.Order;
import com.piatnitsa.entity.Tag;

import java.util.Optional;

/**
 * This interface describes abstract behavior for working with
 *  <code>tag</code> table using custom methods.
 * @author Vlad Piatnitda
 * @version 1.0
 */
public interface CustomTagRepository extends FilteringRepository<Tag> {

    /**
     * Retrieves the most popular {@link Tag} entity with the highest cost of all {@link Order} entities.
     * @return the most popular {@link Tag} entity.
     */
    Optional<Tag> findMostPopularTagWithHighestCostOfAllOrders();

}
