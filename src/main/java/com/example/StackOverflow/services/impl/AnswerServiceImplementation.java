package com.example.StackOverflow.services.impl;

import com.example.StackOverflow.dtos.AnswerDTO;
import com.example.StackOverflow.dtos.UserDTO;
import com.example.StackOverflow.entities.Answer;
import com.example.StackOverflow.entities.User;
import com.example.StackOverflow.entities.VoteAnswer;
import com.example.StackOverflow.exceptions.CustomException;
import com.example.StackOverflow.repositories.IAnswerRepo;
import com.example.StackOverflow.repositories.IUserRepo;
import com.example.StackOverflow.repositories.IVoteAnswerRepo;
import com.example.StackOverflow.services.IAnswerService;
import com.example.StackOverflow.services.builders.AnswerBuilder;
import com.example.StackOverflow.services.builders.UserBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AnswerServiceImplementation implements IAnswerService {
    private final IAnswerRepo answerRepo;
    private final IVoteAnswerRepo voteAnswerRepo;
    private final IUserRepo userRepo;

    @Override
    public List<AnswerDTO> getAllAnswers() {
        return answerRepo.findAll()
                .stream()
                .map(AnswerBuilder::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AnswerDTO editAnswer(AnswerDTO answerDTO) {
        Answer answer = AnswerBuilder.toEntity(answerDTO);

        return AnswerBuilder.toDTO(answerRepo.save(answer));
    }

    @Override
    public Long insertAnswer(AnswerDTO answerDTO) {
        Answer answer = AnswerBuilder.toEntity(answerDTO);

        return answerRepo.save(answer).getId();
    }

    @Override
    public void deleteAnswer(Long id) {
        answerRepo.deleteById(id);
    }

    @Override
    public AnswerDTO findAnswerById(Long id) {
        Optional<Answer> answerOptional = answerRepo.findById(id);

        return AnswerBuilder.toDTO(answerOptional.get());
    }

    @Override
    public List<AnswerDTO> getAnswersByUser(Long userId) {
        List<Answer> answers = answerRepo.findAll();

        return answers.stream()
                .filter(a -> a.getUser().getId() == userId)
                .map(AnswerBuilder::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<AnswerDTO> getAnswersByQuestion(Long questionId) {
        List<Answer> answers = answerRepo.findAll();

        return answers.stream()
                .filter(a -> a.getQuestion().getId() == questionId)
                .map(AnswerBuilder::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void upvoteAnswer(UserDTO userDTO, AnswerDTO answerDTO) throws CustomException {
        List<VoteAnswer> voteAnswers = voteAnswerRepo.findAll();
        List<VoteAnswer> existingVoteAnswers = voteAnswers.stream()
                .filter(va -> va.getUser().getId() == userDTO.getId() && va.getAnswer().getId() == answerDTO.getId())
                .collect(Collectors.toList());

        if(!existingVoteAnswers.isEmpty()) {
            throw new CustomException("You already voted this answer!");
        }

        VoteAnswer voteAnswer = new VoteAnswer();
        voteAnswer.setUser(UserBuilder.toEntity(userDTO));
        voteAnswer.setAnswer(AnswerBuilder.toEntity(answerDTO));
        voteAnswer.setUpvote(true);
        voteAnswerRepo.save(voteAnswer);

        Answer answer = AnswerBuilder.toEntity(answerDTO);
        answer.setVotes(answer.getVotes() + 1);
        answerRepo.save(answer);

        User answerUser = UserBuilder.toEntity(answerDTO.getUser());
        answerUser.setScore(answerUser.getScore() + 10);
        userRepo.save(answerUser);
    }

    @Override
    public void downvoteAnswer(UserDTO userDTO, AnswerDTO answerDTO) throws CustomException {
        List<VoteAnswer> voteAnswers = voteAnswerRepo.findAll();
        List<VoteAnswer> existingVoteAnswers = voteAnswers.stream()
                .filter(va -> va.getUser().getId() == userDTO.getId() && va.getAnswer().getId() == answerDTO.getId())
                .collect(Collectors.toList());

        if(!existingVoteAnswers.isEmpty()) {
            throw new CustomException("You already voted this answer!");
        }

        VoteAnswer voteAnswer = new VoteAnswer();
        voteAnswer.setUser(UserBuilder.toEntity(userDTO));
        voteAnswer.setAnswer(AnswerBuilder.toEntity(answerDTO));
        voteAnswer.setUpvote(false);
        voteAnswerRepo.save(voteAnswer);

        Answer answer = AnswerBuilder.toEntity(answerDTO);
        answer.setVotes(answer.getVotes() - 1);
        answerRepo.save(answer);

        User answerUser = UserBuilder.toEntity(answerDTO.getUser());
        answerUser.setScore(answerUser.getScore() - 2);
        userRepo.save(answerUser);

        User user = UserBuilder.toEntity(userDTO);
        user.setScore(user.getScore() - 1);
    }
}
