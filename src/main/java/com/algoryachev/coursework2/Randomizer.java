package com.algoryachev.coursework2;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;
@Component
public class Randomizer {
    private final Random random = new Random();

    public Question getRandomQuestion(List<Question> questions) {
        if (questions.isEmpty()) {
            return null;
        }
        int index = random.nextInt(questions.size());
        return questions.get(index);
    }

    public Random getRandom() {
        return random;
    }
}