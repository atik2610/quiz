package com.example.demo.service;

import com.example.demo.entity.Quiz;
import com.example.demo.entity.User;
import com.example.demo.repository.QuizRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final UserRepository userRepository;

    public QuizService(QuizRepository quizRepository, UserRepository userRepository) {
        this.quizRepository = quizRepository;
        this.userRepository = userRepository;
    }

    // CREATE QUIZ FOR USER
    public Quiz createQuizForUser(Long userId, Quiz quiz) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        quiz.setOwner(user);
        user.getQuizzes().add(quiz);

        return quizRepository.save(quiz);
    }

    // GET ALL QUIZZES
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    // GET QUIZ BY ID
    public Quiz getQuizById(Long id) {
        return quizRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));
    }

    // GET QUIZZES BY USER
    public List<Quiz> getQuizzesByUserId(Long userId) {
        return quizRepository.findByOwner_Id(userId);
    }

    // UPDATE QUIZ (safe)
    public Quiz updateQuiz(Long id, Quiz updatedQuiz) {
        Quiz quiz = getQuizById(id);
        quiz.setTitle(updatedQuiz.getTitle());
        return quizRepository.save(quiz);
    }

    // DELETE QUIZ
    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }
}