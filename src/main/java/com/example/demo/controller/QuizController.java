package com.example.demo.controller;

import com.example.demo.entity.Quiz;
import com.example.demo.service.QuizService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    // CREATE QUIZ FOR USER
    @PostMapping("/user/{userId}")
    public Quiz createQuizForUser(@PathVariable Long userId,
                                  @RequestBody Quiz quiz) {
        return quizService.createQuizForUser(userId, quiz);
    }

    // GET ALL QUIZZES
    @GetMapping
    public List<Quiz> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    // GET QUIZZES OF USER
    @GetMapping("/user/{userId}")
    public List<Quiz> getQuizzesByUser(@PathVariable Long userId) {
        return quizService.getQuizzesByUserId(userId);
    }

    // GET QUIZ BY ID
    @GetMapping("/{id}")
    public Quiz getQuizById(@PathVariable Long id) {
        return quizService.getQuizById(id);
    }

    // UPDATE QUIZ
    @PutMapping("/{id}")
    public Quiz updateQuiz(@PathVariable Long id,
                           @RequestBody Quiz quiz) {
        return quizService.updateQuiz(id, quiz);
    }

    // DELETE QUIZ
    @DeleteMapping("/{id}")
    public String deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuiz(id);
        return "Quiz deleted successfully";
    }
}