package tw.tutorlink.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tw.tutorlink.bean.Report;

public interface ReportDAO extends JpaRepository<Report, Integer> {
	@Query("SELECT r FROM Users u JOIN u.report r")
	List<Report> findAllReportList();
	
	@Query("SELECT r FROM Users u JOIN u.report r WHERE u.usersId = :usersId")
	List<Report> findReportListByUsersId(@Param("usersId") Integer usersId);
	
	@Query("SELECT r FROM Lessons l JOIN l.report r WHERE l.lessonId = :lessonId")
	List<Report> findReportListByLessonId(@Param("lessonId") Integer lessonId);
}
