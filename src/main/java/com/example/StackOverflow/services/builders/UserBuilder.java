package com.example.StackOverflow.services.builders;

import com.example.StackOverflow.dtos.UserDTO;
import com.example.StackOverflow.entities.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserBuilder {
    public UserDTO toDTO(User user) {
        return UserDTO.builder()
                .email(user.getEmail())
                .id(user.getId())
                .isAdmin(user.isAdmin())
                .isBanned(user.isBanned())
                .name(user.getName())
                .password(user.getPassword())
                .score(user.getScore())
                .username(user.getUsername())
                .build();
    }

    public User toEntity(UserDTO userDTO) {
        return User.builder()
                .email(userDTO.getEmail())
                .id(userDTO.getId())
                .isAdmin(userDTO.isAdmin())
                .isBanned(userDTO.isBanned())
                .name(userDTO.getName())
                .password(userDTO.getPassword())
                .score(userDTO.getScore())
                .username(userDTO.getUsername())
                .build();
    }
}
