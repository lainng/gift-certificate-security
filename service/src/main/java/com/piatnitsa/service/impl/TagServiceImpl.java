package com.piatnitsa.service.impl;

import com.piatnitsa.dao.TagRepository;
import com.piatnitsa.entity.Tag;
import com.piatnitsa.exception.*;
import com.piatnitsa.service.AbstractService;
import com.piatnitsa.service.TagService;
import com.piatnitsa.validator.FilterParameterValidator;
import com.piatnitsa.validator.TagValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl extends AbstractService<Tag> implements TagService {
    private final TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        super(tagRepository);
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag insert(Tag entity) {
        ExceptionMessageHolder exceptionMessageHolder = TagValidator.validate(entity);
        if (exceptionMessageHolder.hasMessages()) {
            throw new IncorrectParameterException(exceptionMessageHolder);
        }
        boolean isTagExist = tagRepository.findTagByName(entity.getName()).isPresent();
        if (isTagExist) {
            throw new DuplicateEntityException(ExceptionMessageKey.TAG_EXIST);
        }
        entity.setName(capitalizeTagName(entity.getName()));
        return tagRepository.save(entity);
    }

    @Override
    public List<Tag> doFilter(MultiValueMap<String, String> params, int page, int size) {
        ExceptionMessageHolder holder = FilterParameterValidator.validateSortType(params);
        holder.putAll(FilterParameterValidator.validatePaginationParameters(page, size).getMessages());
        if (holder.hasMessages()) {
            throw new IncorrectParameterException(holder);
        }
        removeDuplicatePaginationParams(params);
        PageRequest pageRequest = PageRequest.of(page, size);
        return tagRepository.findWithFilter(params, pageRequest);
    }

    @Override
    public Tag getMostPopularTagWithHighestCostOfAllOrders() {
        Optional<Tag> optionalTag = tagRepository.findMostPopularTagWithHighestCostOfAllOrders();
        if (!optionalTag.isPresent()) {
            throw new NoSuchEntityException(ExceptionMessageKey.TAG_NOT_FOUND);
        }
        return optionalTag.get();
    }

    private String capitalizeTagName(String tagName) {
        return tagName.substring(0, 1).toUpperCase() + tagName.substring(1);
    }
}
