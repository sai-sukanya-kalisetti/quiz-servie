package com.example.quizservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.quizservice.dao.QuizDao;
import com.example.quizservice.feign.QuizInterface;
import com.example.quizservice.model.QuestionWrapper;
import com.example.quizservice.model.Quiz;
import com.example.quizservice.model.Response;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;
    
    @Autowired
    QuizInterface quizInterface;
   
    //we get ResponseEntity - get only body i.e Id's of Questions
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
    	List<Integer> questionIds = quizInterface.generateQuestionsForQuiz(category, numQ).getBody();
    	
    	Quiz quiz  = new Quiz();
    	quiz.setTitle(title); 
    	quiz.setQuestionIds(questionIds);
    	
    	quizDao.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
    	Quiz quiz = quizDao.findById(id).get();
        List<Integer> questionsIds= quiz.getQuestionIds();
        
        ResponseEntity<List<QuestionWrapper>> questionsForUser = quizInterface.getQuestionFromId(questionsIds);

        return questionsForUser;
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
    	ResponseEntity<Integer> score = quizInterface.getScore(responses);
    	
        return score;
    }
}
