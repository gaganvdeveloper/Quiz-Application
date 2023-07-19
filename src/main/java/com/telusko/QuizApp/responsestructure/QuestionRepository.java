package com.telusko.QuizApp.responsestructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.telusko.QuizApp.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

//	 @Query("select q from Question q where q.category=?1")
	List<Question> findByCategory(String category);
	
//	@Query(value = "SELECT * FROM question q Where q.category=:category ORDER BY RANDOM() LIMIT :numQ ",nativeQuery = true)
	@Query("select q from Question q where q.category=:category order by random() limit :numQ")
	List<Question> findRandomQuestions(String category, int numQ);
	
	
	
}
