package com.algoryachev.coursework2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/java-questions")
public class JavaQuestionController {

    private final QuestionService questionService;

    @Autowired
    public JavaQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    // add new question
    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        questionService.addQuestion(question);
        return new ResponseEntity<>("Question added successfully", HttpStatus.CREATED);
    }

    // get all questions
    @GetMapping("/all")
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    // delete question by index
    @DeleteMapping("/delete/{index}")
    public ResponseEntity<String> deleteQuestionByIndex(@PathVariable int index) {
        Question question = questionService.getQuestionByIndex(index);
        if (question != null) {
            ((JavaQuestionService) questionService).deleteQuestionByIndex(index);
            return new ResponseEntity<>("Question deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Question not found", HttpStatus.NOT_FOUND);
    }
}
