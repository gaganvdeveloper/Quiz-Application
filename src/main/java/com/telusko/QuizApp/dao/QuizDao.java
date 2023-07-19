package com.telusko.QuizApp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.telusko.QuizApp.model.Quiz;
import com.telusko.QuizApp.responsestructure.QuizRepository;

@Repository
public class QuizDao {
	
	@Autowired
	QuizRepository repository;
	
	public Quiz createQuiz(Quiz quiz) {
		return repository.save(quiz);
	}

	
	
	
}
