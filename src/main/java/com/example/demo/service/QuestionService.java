package com.example.demo.service;

import com.example.demo.entity.Question;
import com.example.demo.entity.Quiz;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;

    public QuestionService(QuestionRepository questionRepository,
                           QuizRepository quizRepository) {
        this.questionRepository = questionRepository;
        this.quizRepository = quizRepository;
    }

    // CREATE QUESTION FOR QUIZ
    public Question createQuestion(Long quizId, Question question) {

        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new RuntimeException("Quiz not found"));

        question.setQuiz(quiz);
        quiz.getQuestions().add(question);

        return questionRepository.save(question);
    }

    // GET ALL QUESTIONS
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    // GET QUESTION BY ID
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
    }

    // GET QUESTIONS BY QUIZ
    public List<Question> getQuestionsByQuizId(Long quizId) {
        return questionRepository.findByQuiz_Id(quizId);
    }

    // UPDATE QUESTION
    public Question updateQuestion(Long id, Question updated) {

        Question q = getQuestionById(id);

        q.setQuestionText(updated.getQuestionText());
        q.setOptionA(updated.getOptionA());
        q.setOptionB(updated.getOptionB());
        q.setOptionC(updated.getOptionC());
        q.setOptionD(updated.getOptionD());
        q.setCorrectAnswer(updated.getCorrectAnswer());

        return questionRepository.save(q);
    }

    // DELETE QUESTION
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
}