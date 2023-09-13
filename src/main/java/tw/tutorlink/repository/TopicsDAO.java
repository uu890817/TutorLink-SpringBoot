package tw.tutorlink.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tw.tutorlink.bean.Topics;

public interface TopicsDAO extends JpaRepository<Topics, Integer> {

	
	@Query("FROM Topics t WHERE t.exercises.exerId = :tid")
	public List<Topics> findByExerciseId(@Param("tid") Integer tid);
	
	@Query("FROM Topics t WHERE t.topicsId = :tid")
	public Topics findBytId (@Param("tid") Integer tid);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
