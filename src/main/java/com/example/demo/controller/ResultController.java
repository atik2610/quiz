package com.example.demo.controller;

import com.example.demo.dto.QuizResult;
import com.example.demo.service.ResultService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/result")
public class ResultController {

    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping
    public QuizResult getResult(@RequestParam Long userId,
                                @RequestParam Long quizId) {
        return resultService.calculateResult(userId, quizId);
    }
}