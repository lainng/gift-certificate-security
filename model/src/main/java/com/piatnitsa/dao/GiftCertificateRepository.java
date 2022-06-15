package com.piatnitsa.dao;

import com.piatnitsa.entity.GiftCertificate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GiftCertificateRepository extends JpaRepository<GiftCertificate, Long>, FilteringRepository<GiftCertificate> {

    /**
     * Retrieves an {@link GiftCertificate} entity by its name.
     * @param name entity name.
     * @return an {@link GiftCertificate} entity.
     */
    Optional<GiftCertificate> findByName(String name);
}
