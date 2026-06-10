package com.example.demo.controller;

import com.example.demo.service.ResultService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/result")
public class ResultController {

    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping
    public Object getResult(@RequestParam Long quizId,
                            HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return "Please login first";
        }

        return resultService.calculateResult(userId, quizId);
    }
}

