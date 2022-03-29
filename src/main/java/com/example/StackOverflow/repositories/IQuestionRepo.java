package com.example.StackOverflow.repositories;

import com.example.StackOverflow.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IQuestionRepo extends JpaRepository<Question, Long> {
}
