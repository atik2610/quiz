package com.example.demo.controller;

import com.example.demo.entity.Question;
import com.example.demo.service.QuestionService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    // CREATE QUESTION FOR QUIZ
    @PostMapping("/quiz/{quizId}")
    public Object createQuestion(@PathVariable Long quizId,
                                 @RequestBody Question question,
                                 HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return "Please login first";
        }

        return questionService.createQuestion(quizId, question);
    }

    // GET ALL QUESTIONS
    @GetMapping
    public Object getAllQuestions(HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return "Please login first";
        }

        return questionService.getAllQuestions();
    }

    // GET QUESTION BY ID
    @GetMapping("/{id}")
    public Object getQuestionById(@PathVariable Long id,
                                  HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return "Please login first";
        }

        return questionService.getQuestionById(id);
    }

    // GET QUESTIONS BY QUIZ
    @GetMapping("/quiz/{quizId}")
    public Object getQuestionsByQuiz(@PathVariable Long quizId,
                                     HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return "Please login first";
        }

        return questionService.getQuestionsByQuizId(quizId);
    }

    // UPDATE QUESTION
    @PutMapping("/{id}")
    public Object updateQuestion(@PathVariable Long id,
                                 @RequestBody Question question,
                                 HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return "Please login first";
        }

        return questionService.updateQuestion(id, question);
    }

    // DELETE QUESTION
    @DeleteMapping("/{id}")
    public Object deleteQuestion(@PathVariable Long id,
                                 HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return "Please login first";
        }

        questionService.deleteQuestion(id);
        return "Question deleted successfully";
    }
}