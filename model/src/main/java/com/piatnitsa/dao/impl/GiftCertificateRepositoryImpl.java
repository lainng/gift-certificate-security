package com.piatnitsa.dao.impl;

import com.piatnitsa.dao.AbstractRepository;
import com.piatnitsa.dao.FilteringRepository;
import com.piatnitsa.dao.creator.QueryCreator;
import com.piatnitsa.entity.GiftCertificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GiftCertificateRepositoryImpl
        extends AbstractRepository<GiftCertificate>
        implements FilteringRepository<GiftCertificate> {

    @Autowired
    public GiftCertificateRepositoryImpl(QueryCreator<GiftCertificate> queryCreator) {
        super(queryCreator, GiftCertificate.class);
    }

}
