package tw.tutorlink.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.tutorlink.bean.CourseQA;
import tw.tutorlink.bean.Users;

public interface CourseQADAO extends JpaRepository<CourseQA, Integer> {
	
	//查詢此課程所有問答
	List<CourseQA> findByLesson_LessonId(Integer lessonId);
	
	//老師所有課程的問答
    List<CourseQA> findByLesson_Users(Users user);
}
