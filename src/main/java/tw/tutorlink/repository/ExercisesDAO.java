package tw.tutorlink.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tw.tutorlink.bean.Exercises;
import tw.tutorlink.bean.Lessons;

public interface ExercisesDAO extends JpaRepository<Exercises, Integer> {
	
	@Query("FROM Exercises e JOIN e.lesson WHERE e.users.usersId = :usersId")
	public List<Exercises> findByUsers(@Param("usersId") Integer usersId);
	
	@Query("FROM Lessons l WHERE l.users.usersId = :usersId")
	public List<Lessons> findLessonsByUsers(@Param("usersId") Integer usersId);
	
	@Query("FROM Exercises e WHERE e.exerId = :exerId")
	public Exercises findExercisesByExerId(@Param("exerId") Integer exerId);
	
	
	
	
	
	
	
	
	
	
	
	
	
}
