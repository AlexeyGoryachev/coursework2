package com.algoryachev.coursework2;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JavaQuestionService implements QuestionService {

    private final List<Question> questions = new ArrayList<>();
    private final Randomizer randomizer;

    public JavaQuestionService(Randomizer randomizer) {
        this.randomizer = randomizer;
    }

    @Override
    public void addQuestion(Question question) {
        questions.add(question);
    }

    @Override
    public List<Question> getAllQuestions() {
        return new ArrayList<>(questions);
    }

    @Override
    public Question getRandomQuestion() {
        return randomizer.getRandomQuestion(questions);
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
