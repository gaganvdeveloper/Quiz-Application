package com.telusko.QuizApp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.telusko.QuizApp.model.Question;
import com.telusko.QuizApp.responsestructure.QuestionRepository;

@Repository
public class QuestionDao {
	@Autowired
	QuestionRepository repository;

	public Question saveQuestion(Question question) {
		return repository.save(question);
	}
	
	public Question updateQuestion(Question question) {
		return repository.save(question);
	}
	
	public Optional<Question> findQuestionById(int id) {
		return repository.findById(id);
	}

	public List<Question> getAllQuestions() {
		return repository.findAll(); 
	}

	public List<Question> findByCategory(String category){
		return repository.findByCategory(category);
	}

	public List<Question> findRandomQuestions(String category, int numQ) {
		return repository.findRandomQuestions( category, numQ);
	}
	
}
