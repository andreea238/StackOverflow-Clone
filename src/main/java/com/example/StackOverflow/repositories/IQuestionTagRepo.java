package com.example.StackOverflow.repositories;

import com.example.StackOverflow.entities.QuestionTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IQuestionTagRepo extends JpaRepository<QuestionTag, Long> {
}
