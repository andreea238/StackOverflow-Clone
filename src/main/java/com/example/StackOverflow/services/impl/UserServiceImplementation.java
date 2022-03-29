package com.example.StackOverflow.services.impl;

import com.example.StackOverflow.dtos.UserDTO;
import com.example.StackOverflow.entities.User;
import com.example.StackOverflow.repositories.IUserRepo;
import com.example.StackOverflow.services.IUserService;
import com.example.StackOverflow.services.builders.UserBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImplementation implements IUserService {
    private final IUserRepo userRepo;

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepo.findAll()
                .stream()
                .map(UserBuilder::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO findUserById(Long id) {
        Optional<User> userOptional = userRepo.findById(id);

        return UserBuilder.toDTO(userOptional.get());
    }

    @Override
    public UserDTO editUser(UserDTO userDTO) {
        User user = UserBuilder.toEntity(userDTO);

        return UserBuilder.toDTO(userRepo.save(user));
    }

    @Override
    public Long insertUser(UserDTO userDTO) {
        User user = UserBuilder.toEntity(userDTO);

        return userRepo.save(user).getId();
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}
