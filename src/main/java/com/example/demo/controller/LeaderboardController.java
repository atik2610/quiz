package com.example.demo.controller;

import com.example.demo.dto.LeaderboardEntry;
import com.example.demo.service.LeaderboardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardController {

    private final LeaderboardService leaderboardService;

    public LeaderboardController(LeaderboardService leaderboardService) {
        this.leaderboardService = leaderboardService;
    }

    @GetMapping("/{quizId}")
    public List<LeaderboardEntry> getLeaderboard(@PathVariable Long quizId) {
        return leaderboardService.getLeaderboard(quizId);
    }
}