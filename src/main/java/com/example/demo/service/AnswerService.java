package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;

    public AnswerService(AnswerRepository answerRepository,
                         UserRepository userRepository,
                         QuizRepository quizRepository,
                         QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
    }

    // CREATE (SUBMIT ANSWER)
    public Answer createAnswer(Long userId, Long quizId, Long questionId, String selectedAnswer) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));

        Answer answer = new Answer();
        answer.setUser(user);
        answer.setQuiz(quiz);
        answer.setQuestion(question);
        answer.setSelectedAnswer(selectedAnswer);

        boolean correct = question.getCorrectAnswer().equals(selectedAnswer);
        answer.setCorrect(correct);

        return answerRepository.save(answer);
    }

    // READ ALL
    public List<Answer> getAllAnswers() {
        return answerRepository.findAll();
    }

    // READ BY ID
    public Answer getAnswerById(Long id) {
        return answerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Answer not found"));
    }

    // READ BY USER + QUIZ
    public List<Answer> getAnswersByUserAndQuiz(Long userId, Long quizId) {
        return answerRepository.findByUser_IdAndQuiz_Id(userId, quizId);
    }

    // UPDATE ANSWER
    public Answer updateAnswer(Long id, String selectedAnswer) {

        Answer answer = getAnswerById(id);

        answer.setSelectedAnswer(selectedAnswer);

        // re-check correctness
        boolean correct = answer.getQuestion()
                .getCorrectAnswer()
                .equals(selectedAnswer);

        answer.setCorrect(correct);

        return answerRepository.save(answer);
    }

    // DELETE ANSWER
    public void deleteAnswer(Long id) {
        answerRepository.deleteById(id);
    }
}