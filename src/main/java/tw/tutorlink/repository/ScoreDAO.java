package tw.tutorlink.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import tw.tutorlink.bean.Comment;

public interface ScoreDAO extends JpaRepository<Comment, Integer> {
	@Query("SELECT c FROM Users u JOIN u.comment c")
	List<Comment> findAllCommentList();
}
