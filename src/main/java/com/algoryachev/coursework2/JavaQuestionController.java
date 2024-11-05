package com.algoryachev.coursework2;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/java-questions")
public class JavaQuestionController {

    private final QuestionService questionService;

    @Autowired
    public JavaQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Operation(summary = "Get a random set of questions", description = "Returns a list of random questions, with the amount specified by the user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Questions retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid number of questions requested")
    })

    // add new question
    @PostMapping("/java/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) {
        questionService.addQuestion(question);
        return new ResponseEntity<>("Question added successfully", HttpStatus.CREATED);
    }

    // get all questions
    @GetMapping("/java/find")
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    // delete question by index
    @DeleteMapping("/java/remove/{index}")
    public ResponseEntity<String> deleteQuestionByIndex(@PathVariable int index) {
        Question question = questionService.getQuestionByIndex(index);
        if (question != null) {
            throw new NoSuchElementException("Question with index " + index + " not found.");
        }
        questionService.deleteQuestionByIndex(index);
        return new ResponseEntity<>("Question deleted successfully", HttpStatus.OK);
    }
}