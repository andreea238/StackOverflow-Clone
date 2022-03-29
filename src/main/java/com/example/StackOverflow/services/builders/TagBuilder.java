package com.example.StackOverflow.services.builders;

import com.example.StackOverflow.dtos.TagDTO;
import com.example.StackOverflow.entities.Tag;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TagBuilder {
    public TagDTO toDTO(Tag tag) {
        return TagDTO.builder()
                .id(tag.getId())
                .name(tag.getName())
                .build();
    }

    public Tag toEntity(TagDTO tagDTO) {
        return Tag.builder()
                .id(tagDTO.getId())
                .name(tagDTO.getName())
                .build();
    }
}
