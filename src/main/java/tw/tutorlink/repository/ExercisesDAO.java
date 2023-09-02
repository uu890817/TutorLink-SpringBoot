package tw.tutorlink.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tw.tutorlink.bean.Exercises;

public interface ExercisesDAO extends JpaRepository<Exercises, Integer> {
	
	@Query("FROM Exercises e JOIN e.lesson WHERE e.users.usersId = :usersId")
	public List<Exercises> findByUsers(@Param("usersId") Integer usersId);
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
