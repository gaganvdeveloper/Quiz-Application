package com.telusko.QuizApp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telusko.QuizApp.model.Question;
import com.telusko.QuizApp.responsestructure.ResponseStructure;
import com.telusko.QuizApp.service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	QuestionService service;

	@PostMapping("/add")
	public ResponseEntity<ResponseStructure<Question>> saveQuestion(@RequestBody Question question) {
		return service.saveQuestion(question);
	}
	@PutMapping("/add")
	public ResponseEntity<ResponseStructure<Question>> updateQuestion(@RequestBody Question question) {
		return service.updateQuestion(question);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Optional<Question>>> findQuestionById(@PathVariable int id) {
		return service.findQuestionById(id);
	}

	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<Question>>> getAllQuestion() {
		return service.getAllQuestions();
	}

	@GetMapping("/all/{cat}")
	public ResponseEntity<ResponseStructure<List<Question>>> findByCategory(@PathVariable("cat") String category) {
		return service.findByCategory(category);
	}
	
	
	
	
}
