package com.example.demo.controller;

import com.example.demo.entity.Quiz;
import com.example.demo.service.QuizService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    // CREATE QUIZ FOR USER
    @PostMapping("/user/{userId}")
    public Object createQuizForUser(@PathVariable Long userId,
                                    @RequestBody Quiz quiz,
                                    HttpSession session) {

        Long sessionUserId = (Long) session.getAttribute("userId");

        if (sessionUserId == null) {
            return "Please login first";
        }

        return quizService.createQuizForUser(sessionUserId, quiz);
    }

    // GET ALL QUIZZES
    @GetMapping
    public Object getAllQuizzes(HttpSession session) {

        Long sessionUserId = (Long) session.getAttribute("userId");

        if (sessionUserId == null) {
            return "Please login first";
        }

        return quizService.getAllQuizzes();
    }

    // GET QUIZZES OF USER
    @GetMapping("/user/{userId}")
    public Object getQuizzesByUser(@PathVariable Long userId,
                                   HttpSession session) {

        Long sessionUserId = (Long) session.getAttribute("userId");

        if (sessionUserId == null) {
            return "Please login first";
        }

        return quizService.getQuizzesByUserId(userId);
    }

    // GET QUIZ BY ID
    @GetMapping("/{id}")
    public Object getQuizById(@PathVariable Long id,
                              HttpSession session) {

        Long sessionUserId = (Long) session.getAttribute("userId");

        if (sessionUserId == null) {
            return "Please login first";
        }

        return quizService.getQuizById(id);
    }

    // UPDATE QUIZ
    @PutMapping("/{id}")
    public Object updateQuiz(@PathVariable Long id,
                             @RequestBody Quiz quiz,
                             HttpSession session) {

        Long sessionUserId = (Long) session.getAttribute("userId");

        if (sessionUserId == null) {
            return "Please login first";
        }

        return quizService.updateQuiz(id, quiz);
    }

    // DELETE QUIZ
    @DeleteMapping("/{id}")
    public Object deleteQuiz(@PathVariable Long id,
                             HttpSession session) {

        Long sessionUserId = (Long) session.getAttribute("userId");

        if (sessionUserId == null) {
            return "Please login first";
        }

        quizService.deleteQuiz(id);
        return "Quiz deleted successfully";
    }
}