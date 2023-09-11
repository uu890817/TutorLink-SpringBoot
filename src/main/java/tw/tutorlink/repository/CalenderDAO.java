package tw.tutorlink.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tw.tutorlink.bean.Calender;

public interface CalenderDAO extends JpaRepository<Calender, Integer> {
	@Query("SELECT c FROM Users u JOIN u.calender c")
	List<Calender> findAllReportList();

	@Query("SELECT c FROM Users u JOIN u.calender c WHERE u.usersId = :usersId")
	List<Calender> findCalenderListByUsersId(@Param("usersId") Integer usersId);
	
	@Query("SELECT c FROM Lessons l JOIN l.calender c WHERE l.lessonId = :lessonId")
	List<Calender> findCalenderListByLessonId(@Param("lessonId") Integer lessonId);
}
