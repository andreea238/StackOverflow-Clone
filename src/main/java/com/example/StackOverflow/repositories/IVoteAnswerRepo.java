package com.example.StackOverflow.repositories;

import com.example.StackOverflow.entities.VoteAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVoteAnswerRepo extends JpaRepository<VoteAnswer, Long> {
}
