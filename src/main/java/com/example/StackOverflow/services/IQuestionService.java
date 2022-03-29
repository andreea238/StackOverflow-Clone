package com.example.StackOverflow.services;

import com.example.StackOverflow.dtos.QuestionDTO;
import com.example.StackOverflow.dtos.TagDTO;
import com.example.StackOverflow.dtos.UserDTO;
import com.example.StackOverflow.exceptions.CustomException;

import java.util.List;

public interface IQuestionService {
    List<QuestionDTO> getAllQuestions();
    QuestionDTO findQuestionById(Long id);
    QuestionDTO editQuestion(QuestionDTO questionDTO);
    Long insertQuestion(QuestionDTO questionDTO);
    void deleteQuestion(Long id);
    void upvoteQuestion(UserDTO userDTO, QuestionDTO questionDTO) throws CustomException;
    void downvoteQuestion(UserDTO userDTO, QuestionDTO questionDTO) throws CustomException;
    void tagQuestion(TagDTO tagDTO, QuestionDTO questionDTO);
    List<QuestionDTO> getQuestionsByUser(Long userId);
}
