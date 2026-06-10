package com.example.demo.controller;

import com.example.demo.service.LeaderboardService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardController {

    private final LeaderboardService leaderboardService;

    public LeaderboardController(LeaderboardService leaderboardService) {
        this.leaderboardService = leaderboardService;
    }

    @GetMapping("/{quizId}")
    public Object getLeaderboard(@PathVariable Long quizId,
                                 HttpSession session) {

        Long userId = (Long) session.getAttribute("userId");

        if (userId == null) {
            return "Please login first";
        }

        return leaderboardService.getLeaderboard(quizId);
    }
}