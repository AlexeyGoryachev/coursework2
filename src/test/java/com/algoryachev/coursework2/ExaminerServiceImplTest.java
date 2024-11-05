package com.algoryachev.coursework2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class ExaminerServiceImplTest {
    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Mock
    private QuestionService questionService;

    private List<Question> questions;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        questions = new ArrayList<>();
    }

    @Test
    public void testGetQuestions_Success() {
        questions.add(new Question("What is Java?", "Java is a programming language."));
        questions.add(new Question("What is Spring?", "Spring is a framework."));
        when(questionService.getAllQuestions()).thenReturn(questions);

        List<Question> result = examinerService.getQuestions(1);
        assertEquals(1, result.size());
    }

    @Test
    public void testGetQuestions_InsufficientQuestions() {
        questions.add(new Question("What is Java?", "Java is a programming language."));
        when(questionService.getAllQuestions()).thenReturn(questions);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            examinerService.getQuestions(9);
        });

        String expectedMessage = "Requested amount exceeds available unique questions.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testGetQuestions_UniqueQuestions() {
        questions.add(new Question("What is Java?", "Java is a programming language."));
        questions.add(new Question("What is Java?", "Java is still a programming language.")); // duplicate

        when(questionService.getAllQuestions()).thenReturn(questions);

        Set<Question> uniqueQuestions = new HashSet<>(questions);
        when(questionService.getAllQuestions()).thenReturn(new ArrayList<>(uniqueQuestions));

        List<Question> result = examinerService.getQuestions(1);
        assertEquals(1, result.size());
    }
}
