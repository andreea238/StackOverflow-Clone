package com.example.StackOverflow.dtos;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
    private int score;
    private boolean isAdmin;
    private boolean isBanned;
}
