package com.example.quizservice.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.quizservice.model.QuestionWrapper;
import com.example.quizservice.model.Response;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
	/*get 3 methods of question-service*/
	@GetMapping("question/createQuizQuestions")
    public ResponseEntity<List<Integer>> generateQuestionsForQuiz
    				(@RequestParam String category,@RequestParam Integer numQ) ;
    
    @PostMapping("question/getQuizQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionFromId(@RequestBody List<Integer> questionIds);
    
    @PostMapping("question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
