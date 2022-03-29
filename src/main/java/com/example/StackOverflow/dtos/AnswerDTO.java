package com.example.StackOverflow.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnswerDTO {
    private Long id;
    private String text;
    private LocalDateTime dateCreated;
    private int votes;
    private UserDTO user;
    private QuestionDTO question;
}
