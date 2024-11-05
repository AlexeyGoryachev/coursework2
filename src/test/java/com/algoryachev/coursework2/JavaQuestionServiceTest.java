package com.algoryachev.coursework2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class JavaQuestionServiceTest {
    @InjectMocks
    private JavaQuestionService javaQuestionService;

    @Mock
    private Randomizer randomizer;

    private List<Question> questions;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        questions = new ArrayList<>();
    }

    @Test
    public void testAddQuestion() {
        Question question = new Question("What is Spring?", "Spring is a framework.");
        javaQuestionService.addQuestion(question);
        assertEquals(1, javaQuestionService.getAllQuestions().size());
        assertEquals(question, javaQuestionService.getAllQuestions().get(0));
    }
    @Test
    public void testAddDuplicateQuestion() {
        Question question = new Question("What is Java?", "Java is an object-oriented programming language.");
        javaQuestionService.addQuestion(question);

        Exception exception = assertThrows(DuplicateQuestionException.class, () -> {
            javaQuestionService.addQuestion(question);
        });

        String expectedMessage = "Question already exists: " + question.getQuestion();
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testGetAllQuestions() {
        Question question1 = new Question("What is Java?", "Java is an object-oriented programming language.");
        Question question2 = new Question("What are encapsulation, inheritance and polymorphism?", "These are principles of object-oriented programming.");
        javaQuestionService.addQuestion(question1);
        javaQuestionService.addQuestion(question2);

        List<Question> allQuestions = javaQuestionService.getAllQuestions();
        assertEquals(2, allQuestions.size());
        assertTrue(allQuestions.contains(question1));
        assertTrue(allQuestions.contains(question2));
    }

    @Test
    public void testGetRandomQuestion() {
        Question question1 = new Question("What is Java?", "Java is an object-oriented programming language.");
        javaQuestionService.addQuestion(question1);
        when(randomizer.getRandomQuestion(javaQuestionService.getAllQuestions())).thenReturn(question1);


        Question randomQuestion = javaQuestionService.getRandomQuestion();
        assertEquals(question1, randomQuestion);
    }

    @Test
    public void testGetQuestionByIndex() {
        Question question = new Question("What is Java?", "Java is a programming language.");
        javaQuestionService.addQuestion(question);

        Question foundQuestion = javaQuestionService.getQuestionByIndex(0);
        assertEquals(question, foundQuestion);
    }

    @Test
    public void testDeleteQuestionByIndex() {
        Question question = new Question("What is Java?", "Java is a programming language.");
        javaQuestionService.addQuestion(question);
        javaQuestionService.deleteQuestionByIndex(0);
        assertEquals(0, javaQuestionService.getAllQuestions().size());
    }
}
