package com.example.demo.controller;

import com.example.demo.entity.Answer;
import com.example.demo.service.AnswerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {

    private final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    // CREATE ANSWER
    @PostMapping("/submit")
    public Answer createAnswer(
            @RequestParam Long userId,
            @RequestParam Long quizId,
            @RequestParam Long questionId,
            @RequestParam String selectedAnswer) {

        return answerService.createAnswer(userId, quizId, questionId, selectedAnswer);
    }

    // GET ALL ANSWERS
    @GetMapping
    public List<Answer> getAllAnswers() {
        return answerService.getAllAnswers();
    }

    // GET ANSWER BY ID
    @GetMapping("/{id}")
    public Answer getAnswerById(@PathVariable Long id) {
        return answerService.getAnswerById(id);
    }

    // GET ANSWERS BY USER + QUIZ
    @GetMapping("/user/{userId}/quiz/{quizId}")
    public List<Answer> getAnswersByUserAndQuiz(
            @PathVariable Long userId,
            @PathVariable Long quizId) {

        return answerService.getAnswersByUserAndQuiz(userId, quizId);
    }

    // UPDATE ANSWER
    @PutMapping("/{id}")
    public Answer updateAnswer(
            @PathVariable Long id,
            @RequestParam String selectedAnswer) {

        return answerService.updateAnswer(id, selectedAnswer);
    }

    // DELETE ANSWER
    @DeleteMapping("/{id}")
    public String deleteAnswer(@PathVariable Long id) {
        answerService.deleteAnswer(id);
        return "Answer deleted successfully";
    }
}