package com.piatnitsa.service;

import com.piatnitsa.exception.ExceptionMessageHolder;
import com.piatnitsa.exception.ExceptionMessageKey;
import com.piatnitsa.exception.IncorrectParameterException;
import com.piatnitsa.exception.NoSuchEntityException;
import com.piatnitsa.validator.FilterParameterValidator;
import com.piatnitsa.validator.IdentifiableValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Optional;

/**
 * This class is implementation of interface {@link CRDService} and is designed for basic work with objects.
 * @param <T> type of entity.
 *
 * @author Vlad Piatnitsa
 * @version 1.0
 */
public abstract class AbstractService<T> implements CRDService<T> {
    private final JpaRepository<T, Long> repository;

    protected AbstractService(JpaRepository<T, Long> repository) {
        this.repository = repository;
    }

    @Override
    public T getById(long id) {
        validateId(id);
        Optional<T> entity = repository.findById(id);
        if (!entity.isPresent()) {
            throw new NoSuchEntityException(ExceptionMessageKey.NO_ENTITY);
        }
        return entity.get();
    }

    @Override
    public List<T> getAll(int page, int size) {
        ExceptionMessageHolder holder = FilterParameterValidator.validatePaginationParameters(page, size);
        if (holder.hasMessages()) {
            throw new IncorrectParameterException(holder);
        }
        PageRequest pageRequest = PageRequest.of(page, size);
        return repository.findAll(pageRequest).toList();

    }

    @Override
    public void removeById(long id) {
        validateId(id);
        Optional<T> foundEntity = repository.findById(id);
        if (!foundEntity.isPresent()) {
            throw new NoSuchEntityException(ExceptionMessageKey.NO_ENTITY);
        }
        repository.deleteById(id);
    }

    private void validateId(long id) {
        ExceptionMessageHolder messageHolder = IdentifiableValidator.validateId(id);
        if (messageHolder.hasMessages()) {
            throw new IncorrectParameterException(messageHolder);
        }
    }

    protected void removeDuplicatePaginationParams(MultiValueMap<String, String> params) {
        params.remove("page");
        params.remove("size");
    }
}
