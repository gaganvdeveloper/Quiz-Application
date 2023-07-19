package com.telusko.QuizApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.telusko.QuizApp.dao.QuestionDao;
import com.telusko.QuizApp.dao.QuizDao;
import com.telusko.QuizApp.model.Question;
import com.telusko.QuizApp.model.Quiz;
import com.telusko.QuizApp.responsestructure.ResponseStructure;

@Service
public class QuizService {
	
	
	@Autowired
	QuizDao dao;
	
	@Autowired
	QuestionDao qdao;

	public ResponseEntity<ResponseStructure<Quiz>> createQuiz(String category, int numQ, String title) {
		Quiz quiz=new Quiz();
		List<Question> questions=qdao.findRandomQuestions(category,numQ);
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		
		ResponseStructure<Quiz> structure=new ResponseStructure<>();
		structure.setBody(dao.createQuiz(quiz));
		structure.setMessage("Quiz Created Successfully...");
		structure.setCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Quiz>>(structure,HttpStatus.CREATED);
	}

	
	
	
	
	
}
