package com.example.StackOverflow.dtos;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TagDTO {
    private Long id;
    private String name;
}
