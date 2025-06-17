package com.example.quizservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.quizservice.model.QuestionWrapper;
import com.example.quizservice.model.QuizDTO;
import com.example.quizservice.model.Response;
import com.example.quizservice.service.QuizService;
// controller for quiz
@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    //http://localhost:8090/quiz/create
//    {
//        "category": "java",
//        "numOfQuestions": 3,
//        "title":"Ja_Quiz"
//    }
    @GetMapping("/checkAPI")
    public String check() {
    	return "working";
    }
    
    /* POST http://localhost:8090/quiz/create
     * {
    "category": "android",
    "numOfQuestions": "3",
    "title": "Android_Quiz"
	}
     * */
    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDto){
        return quizService.createQuiz(quizDto.getCategory(),quizDto.getNumOfQuestions(),quizDto.getTitle());
    }
    
    /*GET http://localhost:8090/quiz/get/1
     * */
    @GetMapping("/get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);
    }
}
