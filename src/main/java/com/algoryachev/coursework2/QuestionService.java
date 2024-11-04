package com.algoryachev.coursework2;

import java.util.List;

public interface QuestionService {
    void addQuestion(Question question);
    List<Question> getAllQuestions();
    int getRandomQuestionIndex();
    Question getQuestionByIndex(int index);
}
