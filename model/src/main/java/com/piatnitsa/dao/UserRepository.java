package com.piatnitsa.dao;

import com.piatnitsa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * This interface describes abstract behavior for working with <code>user</code> table in the database.
 *
 * @author Vlad Piatnitsa
 * @version 1.0
 */
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);
}
