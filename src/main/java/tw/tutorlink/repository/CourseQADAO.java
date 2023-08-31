package tw.tutorlink.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.tutorlink.bean.CourseQA;

public interface CourseQADAO extends JpaRepository<CourseQA, Integer> {
	
	//查詢此課程所有問答
	List<CourseQA> findByLesson_LessonId(Integer lessonId);

}
