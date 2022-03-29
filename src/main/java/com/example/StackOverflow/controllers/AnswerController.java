package com.example.StackOverflow.controllers;

import com.example.StackOverflow.dtos.AnswerDTO;
import com.example.StackOverflow.dtos.UserDTO;
import com.example.StackOverflow.exceptions.CustomException;
import com.example.StackOverflow.services.IAnswerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/answer")
@AllArgsConstructor
public class AnswerController {
    private final IAnswerService answerService;

    @GetMapping
    public ResponseEntity<List<AnswerDTO>> getAnswers() {
        List<AnswerDTO> dtos = answerService.getAllAnswers();

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<AnswerDTO> getAnswer(@PathVariable("id") long id) {
        AnswerDTO dto = answerService.findAnswerById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Long> insertAnswer(@RequestBody AnswerDTO answerDTO) {
        long id = answerService.insertAnswer(answerDTO);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteAnswer(@PathVariable("id") long id)
    {
        answerService.deleteAnswer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<AnswerDTO> editAnswer(@RequestBody AnswerDTO answerDTO)
    {
        AnswerDTO dto = answerService.editAnswer(answerDTO);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/byUser/{id}")
    public ResponseEntity<List<AnswerDTO>> getAnswersByUser(@PathVariable("id") long userId) {
        List<AnswerDTO> dtos = answerService.getAnswersByUser(userId);

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping(value = "/byQuestion/{id}")
    public ResponseEntity<List<AnswerDTO>> getAnswersByQuestion(@PathVariable("id") long questionId) {
        List<AnswerDTO> dtos = answerService.getAnswersByQuestion(questionId);

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping(value = "upvoteAnswer")
    public ResponseEntity upvoteAnswer(@RequestBody AnswerDTO answerDTO, UserDTO userDTO) {
        try {
            answerService.upvoteAnswer(userDTO, answerDTO);
        } catch (CustomException e) {
            e.printStackTrace();
            ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(e.getMessage());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "downvoteAnswer")
    public ResponseEntity downvoteAnswer(@RequestBody AnswerDTO answerDTO, UserDTO userDTO) {
        try {
            answerService.downvoteAnswer(userDTO, answerDTO);
        } catch (CustomException e) {
            e.printStackTrace();
            ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(e.getMessage());
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
