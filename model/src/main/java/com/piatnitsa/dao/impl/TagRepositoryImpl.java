package com.piatnitsa.dao.impl;

import com.piatnitsa.dao.AbstractRepository;
import com.piatnitsa.dao.CustomTagRepository;
import com.piatnitsa.dao.creator.QueryCreator;
import com.piatnitsa.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TagRepositoryImpl extends AbstractRepository<Tag> implements CustomTagRepository {
    private static final String QUERY_SELECT_MOST_POPULAR_TAG = "select t from Order o " +
            "join o.certificate c " +
            "join c.tags t " +
            "group by t.id order by count(t.id) desc, sum(o.cost) desc";

    @Autowired
    public TagRepositoryImpl(QueryCreator<Tag> queryCreator) {
        super(queryCreator, Tag.class);
    }

    @Override
    public Optional<Tag> findMostPopularTagWithHighestCostOfAllOrders() {
        return entityManager.createQuery(QUERY_SELECT_MOST_POPULAR_TAG, entityType)
                .getResultStream()
                .findFirst();
    }
}
