package tw.tutorlink.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tw.tutorlink.bean.Comment;

public interface ScoreDAO extends JpaRepository<Comment, Integer> {
	@Query("SELECT c FROM Users u JOIN u.comment c")
	List<Comment> findAllCommentList();
	
	@Query("SELECT c FROM Users u JOIN u.comment c WHERE u.usersId = :usersId")
	List<Comment> findCommentListByUsersId(@Param("usersId") Integer usersId);
	
	@Query("SELECT c FROM Lessons l JOIN l.comment c WHERE l.lessonId = :lessonId")
	List<Comment> findCommentListByLessonId(@Param("lessonId") Integer lessonId);

}
