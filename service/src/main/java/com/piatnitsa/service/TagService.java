package com.piatnitsa.service;

import com.piatnitsa.entity.Tag;

/**
 * This interface describes abstract behavior for working with {@link Tag} objects.
 * @author Vlad Piatnitsa
 * @version 1.0
 */
public interface TagService extends CRDService<Tag> {

    /**
     * Method for getting the most popular {@link Tag} entity with the highest cost of all orders.
     * @return the most popular {@link Tag} entity.
     * @see com.piatnitsa.entity.Order
     */
    Tag getMostPopularTagWithHighestCostOfAllOrders();
}
