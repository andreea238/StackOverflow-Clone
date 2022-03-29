package com.example.StackOverflow.services.builders;

import com.example.StackOverflow.dtos.AnswerDTO;
import com.example.StackOverflow.entities.Answer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AnswerBuilder {
    public AnswerDTO toDTO(Answer answer) {
        return AnswerDTO.builder()
                .id(answer.getId())
                .user(UserBuilder.toDTO(answer.getUser()))
                .dateCreated(answer.getDateCreated())
                .text(answer.getText())
                .votes(answer.getVotes())
                .question(QuestionBuilder.toDTO(answer.getQuestion()))
                .build();
    }

    public Answer toEntity(AnswerDTO answerDTO) {
        return Answer.builder()
                .id(answerDTO.getId())
                .user(UserBuilder.toEntity(answerDTO.getUser()))
                .dateCreated(answerDTO.getDateCreated())
                .question(QuestionBuilder.toEntity(answerDTO.getQuestion()))
                .votes(answerDTO.getVotes())
                .text(answerDTO.getText())
                .build();
    }
}
