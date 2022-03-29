package com.example.StackOverflow.services;

import com.example.StackOverflow.dtos.UserDTO;

import java.util.List;

public interface IUserService {
    List<UserDTO> getAllUsers();
    UserDTO findUserById(Long id);
    UserDTO editUser(UserDTO userDTO);
    Long insertUser(UserDTO userDTO);
    void deleteUser(Long id);
}
