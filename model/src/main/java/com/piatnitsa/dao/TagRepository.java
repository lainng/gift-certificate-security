package com.piatnitsa.dao;

import com.piatnitsa.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface TagRepository extends JpaRepository<Tag, Long>, CustomTagRepository {

    /**
     * Retrieves an {@link Tag} entity by its name.
     * @param name entity name.
     * @return an {@link Tag} entity.
     */
    Optional<Tag> findTagByName(String name);
}
