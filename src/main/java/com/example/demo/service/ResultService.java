package com.example.demo.service;

import com.example.demo.dto.QuizResult;
import com.example.demo.entity.Answer;
import com.example.demo.repository.AnswerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultService {

    private final AnswerRepository answerRepository;

    public ResultService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public QuizResult calculateResult(Long userId, Long quizId) {

        List<Answer> answers =
                answerRepository.findByUser_IdAndQuiz_Id(userId, quizId);

        int total = answers.size();
        int correct = 0;

        for (Answer a : answers) {
            if (a.isCorrect()) {
                correct++;
            }
        }

        int wrong = total - correct;
        double percentage = total == 0 ? 0 : (correct * 100.0 / total);

        return new QuizResult(userId, quizId, total, correct, wrong, percentage);
    }
}