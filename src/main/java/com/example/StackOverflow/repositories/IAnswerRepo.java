package com.example.StackOverflow.repositories;

import com.example.StackOverflow.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAnswerRepo extends JpaRepository<Answer, Long> {
}
