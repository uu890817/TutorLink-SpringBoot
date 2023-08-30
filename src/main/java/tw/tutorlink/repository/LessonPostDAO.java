package tw.tutorlink.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tw.tutorlink.bean.LessonPost;

public interface LessonPostDAO extends JpaRepository<LessonPost, Integer> {

	//查詢此課程公告
	@Query("SELECT lp from LessonPost lp where lp.lessonDetail.id=:lessonDetailid")
	List<LessonPost> findLessonPostByLessonDetailId(@Param("lessonDetailid") Integer lessonDetailid);
	
	//查詢發佈者的公告
	@Query("SELECT lp from LessonPost lp where lp.users.id=:usersid")
	List<LessonPost> findLessonPostByUsersId(@Param("usersid") Integer usersid);
	
}
