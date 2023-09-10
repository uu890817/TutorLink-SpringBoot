package tw.tutorlink.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tw.tutorlink.bean.ExercisePermissions;
import tw.tutorlink.bean.Exercises;
import tw.tutorlink.bean.Lessons;
import tw.tutorlink.bean.OrderItem;

public interface ExercisesDAO extends JpaRepository<Exercises, Integer> {
	
	@Query("FROM Exercises e  WHERE e.users.usersId = :usersId")
	public List<Exercises> findByUsers(@Param("usersId") Integer usersId);
	
	@Query("FROM Lessons l WHERE l.users.usersId = :usersId")
	public List<Lessons> findLessonsByUsers(@Param("usersId") Integer usersId);
	
	@Query("FROM Exercises e WHERE e.exerId = :exerId")
	public Exercises findExercisesByExerId(@Param("exerId") Integer exerId);
	
	@Query("FROM OrderItem o WHERE o.lesson.lessonId = :lessonId")
	public List<OrderItem> findOrderByLessonId(@Param("lessonId") Integer lessonId);
	
	@Query("FROM ExercisePermissions ep WHERE ep.exercises.exerId = :eId AND ep.users.usersId = :uId")
	public ExercisePermissions findExercisePermissionsByLessonIdAndUserId(@Param("eId") Integer eId, @Param("uId") Integer uId);
	
	
	
	
	
	
	
	
	
}
