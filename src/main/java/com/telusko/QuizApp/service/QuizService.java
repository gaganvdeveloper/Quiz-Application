package com.telusko.QuizApp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.telusko.QuizApp.dao.QuestionDao;
import com.telusko.QuizApp.dao.QuizDao;
import com.telusko.QuizApp.model.Question;
import com.telusko.QuizApp.model.QuestionWrapper;
import com.telusko.QuizApp.model.Quiz;
import com.telusko.QuizApp.model.Response;
import com.telusko.QuizApp.responsestructure.ResponseStructure;

@Service
public class QuizService {

	@Autowired
	QuizDao dao;

	@Autowired
	QuestionDao qdao;

	public ResponseEntity<ResponseStructure<Quiz>> createQuiz(String category, int numQ, String title) {
		Quiz quiz = new Quiz();
		List<Question> questions = qdao.findRandomQuestions(category, numQ);
		quiz.setTitle(title);
		quiz.setQuestions(questions);

		ResponseStructure<Quiz> structure = new ResponseStructure<>();
		structure.setBody(dao.createQuiz(quiz));
		structure.setMessage("Quiz Created Successfully...");
		structure.setCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Quiz>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<List<QuestionWrapper>>> getQuizQuestions(int id) {
		ResponseStructure<List<QuestionWrapper>> structure = new ResponseStructure<>();
		Optional<Quiz> recQuiz = dao.findQuizById(id);
		if (recQuiz.isEmpty()) {
			structure.setBody(null);
			structure.setMessage("Inavlaid Question ID : " + id);
			structure.setCode(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<List<QuestionWrapper>>>(structure, HttpStatus.NOT_FOUND);
		}
		List<Question> questionsFromDB = recQuiz.get().getQuestions();
		List<QuestionWrapper> questionsForUser = new ArrayList<>();
		for (Question q : questionsFromDB) {
			QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(),
					q.getOption3(), q.getOption4());
			questionsForUser.add(qw);
		}
		structure.setBody(questionsForUser);
		structure.setMessage("Quiz Found Successfully...");
		structure.setCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<List<QuestionWrapper>>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Integer>> calculateResult(int id, List<Response> responses) {
		ResponseStructure<Integer> structure = new ResponseStructure<>();

		List<Question> questions = dao.findQuizById(id).get().getQuestions();
		int result = 0;
		int i = 0;
		for (Response r : responses) {
			if (r.getResponse().equalsIgnoreCase(questions.get(i).getRightAnswer()))
				result++;
			i++;
		}
		structure.setBody(result);
		structure.setMessage("Result Got Successfully...");
		structure.setCode(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<Integer>>(structure, HttpStatus.OK);
	}

}
