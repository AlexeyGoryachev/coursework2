package com.algoryachev.coursework2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    @Autowired
    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public List<Question> getQuestions(int amount) {
        // Получаем уникальные вопросы, исключая дубликаты по полю "question"
        Set<Question> uniqueQuestions = questionService.getAllQuestions().stream()
                .collect(Collectors.toMap(Question::getQuestion, q -> q, (existing, replacement) -> existing))
                .values()
                .stream()
                .collect(Collectors.toSet());

        // Проверяем, достаточно ли уникальных вопросов
        if (amount > uniqueQuestions.size()) {
            throw new IllegalArgumentException("Запрашиваемое количество вопросов превышает доступное количество уникальных вопросов.");
        }

        // Возвращаем случайные уникальные вопросы
        return uniqueQuestions.stream()
                .limit(amount)
                .collect(Collectors.toList());
    }
}