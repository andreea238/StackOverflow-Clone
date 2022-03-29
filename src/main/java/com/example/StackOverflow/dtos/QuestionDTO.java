package com.example.StackOverflow.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class QuestionDTO {
    private Long id;
    private String title;
    private String text;
    private LocalDateTime dateCreated;
    private int votes;
    private UserDTO user;
}
