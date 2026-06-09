package com.example.demo.service;

import com.example.demo.dto.LeaderboardEntry;
import com.example.demo.entity.Answer;
import com.example.demo.repository.AnswerRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LeaderboardService {

    private final AnswerRepository answerRepository;

    public LeaderboardService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public List<LeaderboardEntry> getLeaderboard(Long quizId) {

        List<Answer> answers =
                answerRepository.findAll()
                        .stream()
                        .filter(a -> a.getQuiz().getId().equals(quizId))
                        .collect(Collectors.toList());

        // Map userId → score
        Map<Long, LeaderboardEntry> map = new HashMap<>();

        for (Answer a : answers) {

            Long userId = a.getUser().getId();
            String userName = a.getUser().getName();

            map.putIfAbsent(userId, new LeaderboardEntry(userId, userName, 0));

            if (a.isCorrect()) {
                map.get(userId).setCorrectAnswers(
                        map.get(userId).getCorrectAnswers() + 1
                );
            }
        }

        // convert to list + sort
        List<LeaderboardEntry> result = new ArrayList<>(map.values());

        result.sort((a, b) -> b.getCorrectAnswers() - a.getCorrectAnswers());

        return result;
    }
}