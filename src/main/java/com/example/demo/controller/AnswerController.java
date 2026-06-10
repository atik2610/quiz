package com.example.demo.controller;

import com.example.demo.service.AnswerService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    // CREATE ANSWER
    @PostMapping("/submit")
    public Object createAnswer(
            @RequestParam Long userId,
            @RequestParam Long quizId,
            @RequestParam Long questionId,
            @RequestParam String selectedAnswer,
            HttpSession session) {

        Long sessionUserId = (Long) session.getAttribute("userId");

        if (sessionUserId == null) {
            return "Please login first";
        }

        return answerService.createAnswer(userId, quizId, questionId, selectedAnswer);
    }

    // GET ALL ANSWERS
    @GetMapping
    public Object getAllAnswers(HttpSession session) {

        Long sessionUserId = (Long) session.getAttribute("userId");

        if (sessionUserId == null) {
            return "Please login first";
        }

        return answerService.getAllAnswers();
    }

    // GET ANSWER BY ID
    @GetMapping("/{id}")
    public Object getAnswerById(@PathVariable Long id,
                                HttpSession session) {

        Long sessionUserId = (Long) session.getAttribute("userId");

        if (sessionUserId == null) {
            return "Please login first";
        }

        return answerService.getAnswerById(id);
    }

    // GET ANSWERS BY USER + QUIZ
    @GetMapping("/user/{userId}/quiz/{quizId}")
    public Object getAnswersByUserAndQuiz(
            @PathVariable Long userId,
            @PathVariable Long quizId,
            HttpSession session) {

        Long sessionUserId = (Long) session.getAttribute("userId");

        if (sessionUserId == null) {
            return "Please login first";
        }

        return answerService.getAnswersByUserAndQuiz(userId, quizId);
    }

    // UPDATE ANSWER
    @PutMapping("/{id}")
    public Object updateAnswer(
            @PathVariable Long id,
            @RequestParam String selectedAnswer,
            HttpSession session) {

        Long sessionUserId = (Long) session.getAttribute("userId");

        if (sessionUserId == null) {
            return "Please login first";
        }

        return answerService.updateAnswer(id, selectedAnswer);
    }

    // DELETE ANSWER
    @DeleteMapping("/{id}")
    public Object deleteAnswer(@PathVariable Long id,
                               HttpSession session) {

        Long sessionUserId = (Long) session.getAttribute("userId");

        if (sessionUserId == null) {
            return "Please login first";
        }

        answerService.deleteAnswer(id);
        return "Answer deleted successfully";
    }
}

