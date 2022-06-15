package com.piatnitsa.dao;

import com.piatnitsa.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * This interface describes abstract behavior for working with <code>order</code> table in the database.
 *
 * @author Vlad Piatnitsa
 * @version 1.0
 */
@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, Long> {

    /**
     * Retrieves a {@link List} of {@link Order} entities
     * which belongs {@link com.piatnitsa.entity.User} with specified ID.
     *
     * @param userId    user ID.
     * @param pageable  an object with pagination info (page number & page size).
     *
     * @return {@link List} of {@link Order} entities.
     */
    List<Order> findByUserId(long userId, Pageable pageable);
}
