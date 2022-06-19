package com.piatnitsa.dao;

import com.piatnitsa.dao.creator.QueryCreator;
import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents basic tools for work with database tables.
 * @param <T> type of entity.
 *
 * @author Vlad Piatnitsa
 * @version 1.0
 */
public abstract class AbstractRepository<T> implements FilteringRepository<T> {

    @PersistenceContext
    protected EntityManager entityManager;
    protected final QueryCreator<T> queryCreator;
    protected final Class<T> entityType;

    public AbstractRepository(QueryCreator<T> queryCreator, Class<T> entityType) {
        this.queryCreator = queryCreator;
        this.entityType = entityType;
    }

    @Override
    public List<T> findWithFilter(MultiValueMap<String, String> params, Pageable pageable) {
        CriteriaQuery<T> criteriaQuery = queryCreator.createFilteringGetQuery(
                params,
                entityManager.getCriteriaBuilder()
        );
        return entityManager.createQuery(criteriaQuery)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultStream()
                .distinct()
                .collect(Collectors.toList());
    }

}
