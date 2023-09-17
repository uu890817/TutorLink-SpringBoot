package tw.tutorlink.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.tutorlink.bean.StudentWillLearn;

public interface StudentWillLearnDAO extends JpaRepository<StudentWillLearn, Integer> {
	
	List<StudentWillLearn> findByLesson_LessonId(Integer lessonid);

	
}
