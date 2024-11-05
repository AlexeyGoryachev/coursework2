package com.algoryachev.coursework2;

import java.util.List;

public interface QuestionService {
    void addQuestion(Question question);
    List<Question> getAllQuestions();
    Question getRandomQuestion();
    Question getQuestionByIndex(int index);
    void deleteQuestionByIndex(int index);
}
