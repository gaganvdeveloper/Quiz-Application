package com.telusko.QuizApp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.telusko.QuizApp.dao.QuestionDao;
import com.telusko.QuizApp.model.Question;
import com.telusko.QuizApp.responsestructure.ResponseStructure;

@Service
public class QuestionService {

	@Autowired
	QuestionDao dao;

	public ResponseEntity<ResponseStructure<Question>> saveQuestion(Question question) {
		ResponseStructure<Question> structure = new ResponseStructure<>();
		structure.setBody(dao.saveQuestion(question));
		structure.setMessage("Question Saved Successfully...");
		structure.setCode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<Question>>(structure, HttpStatus.ACCEPTED);
	}

	public ResponseEntity<ResponseStructure<Question>> updateQuestion(Question question) {
		ResponseStructure<Question> structure = new ResponseStructure<>();
		structure.setBody(dao.updateQuestion(question));
		structure.setMessage("Question Updated Successfully...");
		structure.setCode(HttpStatus.ACCEPTED.value());
		return new ResponseEntity<ResponseStructure<Question>>(structure, HttpStatus.ACCEPTED);
	}

	public ResponseEntity<ResponseStructure<Optional<Question>>> findQuestionById(int id) {
		ResponseStructure<Optional<Question>> structure = new ResponseStructure<>();

		Optional<Question> resQ = dao.findQuestionById(id);
		if (resQ.isPresent()) {
			structure.setBody(resQ);
			structure.setMessage("Question Found Successfully...");
			structure.setCode(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<Optional<Question>>>(structure, HttpStatus.FOUND);
		}
		else {
			structure.setBody(null);
			structure.setMessage("Invalid Question Id "+id);
			structure.setCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<Optional<Question>>>(structure, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<ResponseStructure<List<Question>>> getAllQuestions() {
		ResponseStructure<List<Question>> structure = new ResponseStructure<>();
		structure.setBody(dao.getAllQuestions());
		structure.setMessage("Found All Questions...");
		structure.setCode(HttpStatus.FOUND.value());
		return new ResponseEntity<ResponseStructure<List<Question>>>(structure, HttpStatus.FOUND);
	}

	public ResponseEntity<ResponseStructure<List<Question>>> findByCategory(String category) {
		ResponseStructure<List<Question>> structure = new ResponseStructure<>();
		structure.setBody(dao.findByCategory(category));
		structure.setMessage("Found All Questions...");
		structure.setCode(HttpStatus.FOUND.value());
		return new ResponseEntity<ResponseStructure<List<Question>>>(structure, HttpStatus.FOUND);
	}

}
