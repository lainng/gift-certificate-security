package com.piatnitsa.dao.impl;

import com.piatnitsa.config.DaoTestConfig;
import com.piatnitsa.dao.TagRepository;
import com.piatnitsa.dao.creator.FilterParameter;
import com.piatnitsa.entity.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(classes = DaoTestConfig.class)
@TestPropertySource("classpath:application-test.properties")
@ActiveProfiles("test")
@Transactional
public class TagRepositoryImplTest {
    private static final long NOT_EXISTED_ID = 999L;
    private final Tag TAG_1 = new Tag(1, "tagName1");
    private final Tag TAG_2 = new Tag(2, "tagName3");
    private final Tag TAG_3 = new Tag(3, "tagName5");
    private final Tag TAG_4 = new Tag(4, "tagName4");
    private final Tag TAG_5 = new Tag(5, "tagName2");
    private static final String PART_OF_TAG_NAME = "tagName";
    private static final String NOT_EXISTED_NAME = "not existed name";
    private static final String INCORRECT_FILTER_PARAM = "incorrectParameter";
    private static final String INCORRECT_FILTER_PARAM_VALUE = "incorrectParameterValue";
    private static final String ASCENDING = "ASC";
    private final Pageable pageRequest = PageRequest.of(0, 5);

    @Autowired
    TagRepository tagRepository;

    @Test
    void getById_thenOk() {
        Optional<Tag> expected = Optional.of(TAG_1);
        Optional<Tag> actual = tagRepository.findById(TAG_1.getId());
        assertEquals(expected, actual);
    }

    @Test
    void getByNotExistedId_thenReturnNull() {
        Optional<Tag> actual = tagRepository.findById(NOT_EXISTED_ID);
        assertFalse(actual.isPresent());
    }

    @Test
    void getAll_thenOk() {
        List<Tag> actual = tagRepository.findAll(pageRequest).toList();
        List<Tag> expected = Arrays.asList(TAG_1, TAG_2, TAG_3, TAG_4, TAG_5);
        assertEquals(expected, actual);
    }

    @Test
    void getWithFilter_thenOk() {
        MultiValueMap<String, String> filterParams = new LinkedMultiValueMap<>();
        filterParams.add(FilterParameter.TAG_NAME, PART_OF_TAG_NAME);
        filterParams.add(FilterParameter.SORT_BY_TAG_NAME, ASCENDING);

        List<Tag> actual = tagRepository.findWithFilter(filterParams, pageRequest);
        List<Tag> expected = Arrays.asList(TAG_1, TAG_5, TAG_2, TAG_4, TAG_3);
        assertEquals(expected, actual);
    }

    @Test
    void getWithIncorrectFilter_thenFetchAll() {
        MultiValueMap<String, String> filterParams = new LinkedMultiValueMap<>();
        filterParams.add(INCORRECT_FILTER_PARAM, INCORRECT_FILTER_PARAM_VALUE);

        List<Tag> actual = tagRepository.findWithFilter(filterParams, pageRequest);
        List<Tag> expected = Arrays.asList(TAG_1, TAG_2, TAG_3, TAG_4, TAG_5);
        assertEquals(expected, actual);
    }

    @Test
    void getByName_thenOk() {
        Optional<Tag> expected = Optional.of(TAG_3);
        Optional<Tag> actual = tagRepository.findTagByName(TAG_3.getName());
        assertEquals(expected, actual);
    }

    @Test
    void getByNotExistedName_thenReturnNull() {
        Optional<Tag> actual = tagRepository.findTagByName(NOT_EXISTED_NAME);
        assertFalse(actual.isPresent());
    }

    @Test
    void getMostPopularTagWithHighestCostOfAllOrders_thenOk() {
        Optional<Tag> expected = Optional.of(TAG_2);
        Optional<Tag> actual = tagRepository.findMostPopularTagWithHighestCostOfAllOrders();
        assertEquals(expected, actual);
    }
}