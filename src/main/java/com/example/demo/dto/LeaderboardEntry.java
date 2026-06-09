package com.example.demo.dto;

public class LeaderboardEntry {

    private Long userId;
    private String userName;
    private int correctAnswers;

    public LeaderboardEntry() {}

    public LeaderboardEntry(Long userId, String userName, int correctAnswers) {
        this.userId = userId;
        this.userName = userName;
        this.correctAnswers = correctAnswers;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }
}