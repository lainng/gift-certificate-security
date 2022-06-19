package com.piatnitsa.service;

import com.piatnitsa.entity.GiftCertificate;

/**
 * This interface describes abstract behavior for working with {@link GiftCertificate} objects.
 * @author Vlad Piatnitsa
 * @version 1.0
 */
public interface GiftCertificateService extends CRUDService<GiftCertificate> {

    /**
     * Method for getting a {@link GiftCertificate} entity by the specified name.
     * @param name name of {@link GiftCertificate} entity.
     * @return a {@link GiftCertificate} entity.
     */
    GiftCertificate getByName(String name);
}
