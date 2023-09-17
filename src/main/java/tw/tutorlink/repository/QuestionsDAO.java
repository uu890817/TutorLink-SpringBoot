package tw.tutorlink.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tw.tutorlink.bean.Question;

public interface QuestionsDAO extends JpaRepository<Question, Integer> {

	@Query("FROM Question q WHERE q.exercises.exerId = :eId AND q.isDelete = false")
	public List<Question> findAllQuestionByeId(Integer eId);
	
	
	
	
	
}
