package com.example.StackOverflow.services.impl;

import com.example.StackOverflow.dtos.QuestionDTO;
import com.example.StackOverflow.dtos.TagDTO;
import com.example.StackOverflow.dtos.UserDTO;
import com.example.StackOverflow.entities.Question;
import com.example.StackOverflow.entities.QuestionTag;
import com.example.StackOverflow.entities.User;
import com.example.StackOverflow.entities.VoteQuestion;
import com.example.StackOverflow.exceptions.CustomException;
import com.example.StackOverflow.repositories.*;
import com.example.StackOverflow.services.IQuestionService;
import com.example.StackOverflow.services.builders.QuestionBuilder;
import com.example.StackOverflow.services.builders.TagBuilder;
import com.example.StackOverflow.services.builders.UserBuilder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class QuestionServiceImplementation implements IQuestionService {
    private final IQuestionRepo questionRepo;
    private final IQuestionTagRepo questionTagRepo;
    private final IVoteAnswerRepo voteAnswerRepo;
    private final IVoteQuestionRepo voteQuestionRepo;
    private final IUserRepo userRepo;

    @Override
    public List<QuestionDTO> getAllQuestions() {
        return questionRepo.findAll()
                .stream()
                .map(QuestionBuilder::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public QuestionDTO editQuestion(QuestionDTO questionDTO) {
        Question question = QuestionBuilder.toEntity(questionDTO);

        return QuestionBuilder.toDTO(questionRepo.save(question));
    }

    @Override
    public Long insertQuestion(QuestionDTO questionDTO) {
        Question question = QuestionBuilder.toEntity(questionDTO);

        return questionRepo.save(question).getId();
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepo.deleteById(id);
    }

    @Override
    public QuestionDTO findQuestionById(Long id) {
        Optional<Question> questionOptional = questionRepo.findById(id);

        return QuestionBuilder.toDTO(questionOptional.get());
    }

    @Override
    public List<QuestionDTO> getQuestionsByUser(Long userId) {
        List<Question> questions = questionRepo.findAll();

        return questions.stream()
                .filter(q -> q.getUser().getId() == userId)
                .map(QuestionBuilder::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void tagQuestion(TagDTO tagDTO, QuestionDTO questionDTO) {
        QuestionTag questionTag = new QuestionTag();
        questionTag.setQuestion(QuestionBuilder.toEntity(questionDTO));
        questionTag.setTag(TagBuilder.toEntity(tagDTO));

        questionTagRepo.save(questionTag);
    }

    @Override
    public void upvoteQuestion(UserDTO userDTO, QuestionDTO questionDTO) throws CustomException {
        List<VoteQuestion> voteQuestions = voteQuestionRepo.findAll();
        List<VoteQuestion> existingVoteQuestion = voteQuestions.stream()
                                                        .filter(vq -> vq.getUser().getId() == userDTO.getId() && vq.getQuestion().getId() == questionDTO.getId())
                                                        .collect(Collectors.toList());

        if(!existingVoteQuestion.isEmpty()) {
            throw new CustomException("You already voted this question!");
        }

        VoteQuestion voteQuestion = new VoteQuestion();
        voteQuestion.setQuestion(QuestionBuilder.toEntity(questionDTO));
        voteQuestion.setUser(UserBuilder.toEntity(userDTO));
        voteQuestion.setUpvote(true);
        voteQuestionRepo.save(voteQuestion);

        Question question = QuestionBuilder.toEntity(questionDTO);
        question.setVotes(question.getVotes() + 1);
        questionRepo.save(question);

        User questionUser = UserBuilder.toEntity(questionDTO.getUser());
        questionUser.setScore(questionUser.getScore() + 5);
        userRepo.save(questionUser);
    }

    @Override
    public void downvoteQuestion(UserDTO userDTO, QuestionDTO questionDTO) throws CustomException {
        List<VoteQuestion> voteQuestions = voteQuestionRepo.findAll();
        List<VoteQuestion> existingVoteQuestion = voteQuestions.stream()
                .filter(vq -> vq.getUser().getId() == userDTO.getId() && vq.getQuestion().getId() == questionDTO.getId())
                .collect(Collectors.toList());

        if(!existingVoteQuestion.isEmpty()) {
            throw new CustomException("You already voted this question!");
        }

        VoteQuestion voteQuestion = new VoteQuestion();
        voteQuestion.setQuestion(QuestionBuilder.toEntity(questionDTO));
        voteQuestion.setUser(UserBuilder.toEntity(userDTO));
        voteQuestion.setUpvote(false);
        voteQuestionRepo.save(voteQuestion);

        Question question = QuestionBuilder.toEntity(questionDTO);
        question.setVotes(question.getVotes() - 1);
        questionRepo.save(question);

        User questionUser = UserBuilder.toEntity(questionDTO.getUser());
        questionUser.setScore(questionUser.getScore() - 2);
        userRepo.save(questionUser);
    }
}
