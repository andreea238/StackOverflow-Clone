package com.example.StackOverflow.repositories;

import com.example.StackOverflow.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepo extends JpaRepository<User, Long> {
}
