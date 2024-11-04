package com.algoryachev.coursework2;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class JavaQuestionService implements QuestionService {

    private final List<Question> questions = new ArrayList<>();
    private final Random random = new Random();

    @Override
    public void addQuestion(Question question) {
        questions.add(question);
    }

    @Override
    public List<Question> getAllQuestions() {
        return new ArrayList<>(questions);
    }

    @Override
    public int getRandomQuestionIndex() {
        if (questions.isEmpty()) {
            return -1;
        }
        return random.nextInt(questions.size());
    }

    @Override
    public Question getQuestionByIndex(int index) {
        if (index >= 0 && index < questions.size()) {
            return questions.get(index);
        }
        return null;
    }

    public void deleteQuestionByIndex(int index) {
        if (index >= 0 && index < questions.size()) {
            questions.remove(index);
        }
    }
}
