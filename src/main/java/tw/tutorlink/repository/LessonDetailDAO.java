package tw.tutorlink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tw.tutorlink.bean.LessonDetail;

public interface LessonDetailDAO extends JpaRepository<LessonDetail, Integer> {
//	String findCourseUrlByLesson_LessonId(Integer lessonid);
	
	@Query("SELECT ld.courseUrl FROM LessonDetail ld WHERE ld.lesson.lessonId = :lessonId")
    String findCourseUrlByLessonId(@Param("lessonId") Integer lessonId);

}
