package com.example.StackOverflow.repositories;

import com.example.StackOverflow.entities.VoteQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVoteQuestionRepo extends JpaRepository<VoteQuestion, Long> {
}
