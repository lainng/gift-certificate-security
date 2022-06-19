package com.piatnitsa.dao;

import com.piatnitsa.entity.GiftCertificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * This interface describes abstract behavior for working with <code>gift-certificate</code> table in the database..
 *
 * @author Vlad Piatnitsa
 * @version 1.0
 */
@Repository
@Transactional
public interface GiftCertificateRepository
        extends JpaRepository<GiftCertificate, Long>, FilteringRepository<GiftCertificate> {

    /**
     * Retrieves an {@link GiftCertificate} entity by its name.
     * @param name entity name.
     * @return an {@link GiftCertificate} entity.
     */
    Optional<GiftCertificate> findByName(String name);
}
