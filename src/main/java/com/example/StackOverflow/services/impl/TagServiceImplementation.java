package com.example.StackOverflow.services.impl;

import com.example.StackOverflow.dtos.TagDTO;
import com.example.StackOverflow.entities.QuestionTag;
import com.example.StackOverflow.entities.Tag;
import com.example.StackOverflow.repositories.IQuestionTagRepo;
import com.example.StackOverflow.repositories.ITagRepo;
import com.example.StackOverflow.services.ITagService;
import com.example.StackOverflow.services.builders.TagBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TagServiceImplementation implements ITagService {
    private final ITagRepo tagRepo;
    private final IQuestionTagRepo questionTagRepo;

    @Override
    public List<TagDTO> getAllTags() {
        return tagRepo.findAll()
                .stream()
                .map(TagBuilder::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TagDTO editTag(TagDTO tagDTO) {
        Tag tag = TagBuilder.toEntity(tagDTO);

        return TagBuilder.toDTO(tagRepo.save(tag));
    }

    @Override
    public Long insertTag(TagDTO tagDTO) {
        Tag tag = TagBuilder.toEntity(tagDTO);

        return tagRepo.save(tag).getId();
    }

    @Override
    public void deleteTag(Long id) {
        tagRepo.deleteById(id);
    }

    @Override
    public TagDTO findTagById(Long id) {
        Optional<Tag> tagOptional = tagRepo.findById(id);

        return TagBuilder.toDTO(tagOptional.get());
    }

    @Override
    public List<TagDTO> getTagsByQuestion(Long questionId) {
        List<QuestionTag> questionTags = questionTagRepo.findAll();
        List<Tag> tagsForQuestion = questionTags.stream()
                                            .filter(qt -> qt.getQuestion().getId() == questionId)
                                            .map(qt -> qt.getTag())
                                            .collect(Collectors.toList());

        return tagsForQuestion.stream()
                .map(TagBuilder::toDTO)
                .collect(Collectors.toList());
    }
}
