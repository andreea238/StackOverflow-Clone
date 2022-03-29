package com.example.StackOverflow.services.builders;

import com.example.StackOverflow.dtos.QuestionDTO;
import com.example.StackOverflow.entities.Question;
import lombok.experimental.UtilityClass;

@UtilityClass
public class QuestionBuilder {

    public QuestionDTO toDTO(Question question) {
        return QuestionDTO.builder()
                .id(question.getId())
                .dateCreated(question.getDateCreated())
                .text(question.getText())
                .user(UserBuilder.toDTO(question.getUser()))
                .title(question.getTitle())
                .votes(question.getVotes())
                .build();
    }

    public Question toEntity(QuestionDTO questionDTO) {
        return Question.builder()
                .id(questionDTO.getId())
                .text(questionDTO.getText())
                .title(questionDTO.getTitle())
                .dateCreated(questionDTO.getDateCreated())
                .votes(questionDTO.getVotes())
                .user(UserBuilder.toEntity(questionDTO.getUser()))
                .build();
    }
}
