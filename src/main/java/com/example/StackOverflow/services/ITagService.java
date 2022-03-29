package com.example.StackOverflow.services;

import com.example.StackOverflow.dtos.TagDTO;

import java.util.List;

public interface ITagService {
    List<TagDTO> getAllTags();
    TagDTO findTagById(Long id);
    TagDTO editTag(TagDTO tagDTO);
    Long insertTag(TagDTO tagDTO);
    void deleteTag(Long id);
    List<TagDTO> getTagsByQuestion(Long questionId);
}
