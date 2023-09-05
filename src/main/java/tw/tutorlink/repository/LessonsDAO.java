package tw.tutorlink.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.tutorlink.bean.Lessons;
import tw.tutorlink.bean.Users;

public interface LessonsDAO extends JpaRepository<Lessons, Integer> {
	
	List<Lessons> findByLessonNameContaining(String lessonName);
	
	List<Lessons> findByUsers(Users users);
}
