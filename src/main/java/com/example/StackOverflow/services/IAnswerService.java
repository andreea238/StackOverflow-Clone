package com.example.StackOverflow.services;

import com.example.StackOverflow.dtos.AnswerDTO;
import com.example.StackOverflow.dtos.UserDTO;
import com.example.StackOverflow.exceptions.CustomException;

import java.util.List;

public interface IAnswerService {
    List<AnswerDTO> getAllAnswers();
    AnswerDTO findAnswerById(Long id);
    AnswerDTO editAnswer(AnswerDTO answerDTO);
    Long insertAnswer(AnswerDTO answerDTO);
    void deleteAnswer(Long id);
    List<AnswerDTO> getAnswersByUser(Long userId);
    List<AnswerDTO> getAnswersByQuestion(Long questionId);
    void upvoteAnswer(UserDTO userDTO, AnswerDTO answerDTO) throws CustomException;
    void downvoteAnswer(UserDTO userDTO, AnswerDTO answerDTO) throws CustomException;
}
