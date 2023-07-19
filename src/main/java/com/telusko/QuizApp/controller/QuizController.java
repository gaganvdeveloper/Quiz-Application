package com.telusko.QuizApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.QuizApp.model.Quiz;
import com.telusko.QuizApp.responsestructure.ResponseStructure;
import com.telusko.QuizApp.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {
	@Autowired
	QuizService service;
	
	
	
	@PostMapping("/create")
	public ResponseEntity<ResponseStructure<Quiz>> createQuiz(@RequestParam String category,@RequestParam int numQ, @RequestParam String title){
		return service.createQuiz(category,numQ,title);
	}
	
	
	
}
