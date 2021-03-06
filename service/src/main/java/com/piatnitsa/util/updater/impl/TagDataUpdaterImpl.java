package com.piatnitsa.util.updater.impl;

import com.piatnitsa.dao.TagRepository;
import com.piatnitsa.entity.Tag;
import com.piatnitsa.util.updater.DataUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class TagDataUpdaterImpl implements DataUpdater<Tag> {
    private final TagRepository tagRepository;

    @Autowired
    public TagDataUpdaterImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag updateData(Tag updatableObject, Tag dataObject) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Tag> updateDataList(List<Tag> dataList) {
        if (dataList == null) {
            return null;
        }
        List<Tag> updatableList = new ArrayList<>(dataList.size());
        for (Tag tag : dataList) {
            Optional<Tag> tagFromDb = tagRepository.findTagByName(tag.getName());
            if (tagFromDb.isPresent()) {
                updatableList.add(tagFromDb.get());
            } else {
                tag.setName(capitalizeTagName(tag.getName()));
                updatableList.add(tag);
            }
        }
        return updatableList;
    }

    private String capitalizeTagName(String tagName) {
        return tagName.substring(0, 1).toUpperCase() + tagName.substring(1);
    }
}
