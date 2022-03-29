package com.example.StackOverflow.controllers;

import com.example.StackOverflow.dtos.QuestionDTO;
import com.example.StackOverflow.dtos.TagDTO;
import com.example.StackOverflow.dtos.UserDTO;
import com.example.StackOverflow.exceptions.CustomException;
import com.example.StackOverflow.services.IQuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/question")
@AllArgsConstructor
public class QuestionController {
    private final IQuestionService questionService;

    @GetMapping
    public ResponseEntity<List<QuestionDTO>> getQuestions() {
        List<QuestionDTO> dtos = questionService.getAllQuestions();

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<QuestionDTO> getQuestion(@PathVariable("id") long id) {
        QuestionDTO dto = questionService.findQuestionById(id);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Long> insertQuestion(@RequestBody QuestionDTO questionDTO) {
        long id = questionService.insertQuestion(questionDTO);

        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteQuestion(@PathVariable("id") Long id)
    {
        questionService.deleteQuestion(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<QuestionDTO> editQuestion(@RequestBody QuestionDTO questionDTO)
    {
        QuestionDTO dto = questionService.editQuestion(questionDTO);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping(value = "/tagQuestion")
    public ResponseEntity tagQuestion(@RequestBody QuestionDTO questionDTO, @RequestBody TagDTO tagDTO) {
        questionService.tagQuestion(tagDTO, questionDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/byUser/{id}")
    public ResponseEntity<List<QuestionDTO>> getQuestionsbyUser(@PathVariable("id") Long userId) {
        List<QuestionDTO> dtos = questionService.getQuestionsByUser(userId);

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping(value = "/upvoteQuestion")
    public ResponseEntity upvoteQuestion(@RequestBody QuestionDTO questionDTO, @RequestBody UserDTO userDTO) {
        try {
            questionService.upvoteQuestion(userDTO, questionDTO);
        } catch (CustomException e) {
            e.printStackTrace();
            ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(e.getMessage());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/downvoteQuestion")
    public ResponseEntity downvoteQuestion(@RequestBody QuestionDTO questionDTO, @RequestBody UserDTO userDTO) {
        try {
            questionService.downvoteQuestion(userDTO, questionDTO);
        } catch (CustomException e) {
            e.printStackTrace();
            ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(e.getMessage());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
