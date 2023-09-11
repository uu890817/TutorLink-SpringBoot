package tw.tutorlink.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tw.tutorlink.bean.Lessons;
import tw.tutorlink.bean.Users;

public interface LessonsDAO extends JpaRepository<Lessons, Integer> {
	
	List<Lessons> findByLessonNameContaining(String lessonName);
	
	List<Lessons> findByUsers(Users users);
	

	List<Lessons> findByUsers_UsersIdAndLessonType(Integer userId,boolean lessonType);

	@Query("from Lessons where lessonId = :id")
	public Lessons findById(@Param("id") int id);
	
	@Query("from Lessons l join l.lessondetail where lessonId = :id")
	public Lessons findByIdDetail(@Param("id")int id);
	
	
}
