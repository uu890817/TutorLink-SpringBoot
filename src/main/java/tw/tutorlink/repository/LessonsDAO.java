package tw.tutorlink.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tw.tutorlink.bean.Lessons;

import tw.tutorlink.bean.Users;
import tw.tutorlink.bean.VideoCourseDTO;

public interface LessonsDAO extends JpaRepository<Lessons, Integer> {
	
	List<Lessons> findByLessonNameContaining(String lessonName);
	
	List<Lessons> findByUsers(Users users);
	

	List<Lessons> findByUsers_UsersIdAndLessonType(Integer userId,boolean lessonType);

	@Query("from Lessons where lessonId = :id")
	public Lessons findById(@Param("id") int id);
	
	@Query("from Lessons l join l.lessondetail where lessonId = :id")
	public Lessons findByIdDetail(@Param("id")int id);
	

//	@Query("SELECT new tw.tutorlink.bean.LessonsDTO(l, u) FROM Lessons l JOIN l.users u WHERE l.id = :lessonId")
//	public LessonsDTO findLessonsWithTeacherByLessonId(@Param("lessonId") Integer lessonId);

	//課程id找老師
	@Query("SELECT u FROM Lessons l LEFT JOIN l.users u WHERE l.lessonId = :lessonId")
    Users findUsersByLessonId(@Param("lessonId") Integer lessonId);
	
	@Query("SELECT l.users.usersId FROM Lessons l WHERE l.lessonId = :lessonId")
	Integer findUserIdByLessonId(@Param("lessonId") Integer lessonId);
	
	@Query("SELECT u FROM Lessons l JOIN l.users u WHERE l.lessonId = :lessonId")
	Users findUserByLessonId(@Param("lessonId")Integer lessonId);
	
	//subject找課程
	@Query("FROM Lessons WHERE subject.subjectId = :subjectId AND lessonType = :lessonType")
	List<Lessons> findLessonsBySubIdAndType(@Param("subjectId") Integer subjectId,@Param("lessonType") boolean lessonType);
	
	//Type找課程
	@Query("FROM Lessons WHERE lessonType = :lessonType")
	List<Lessons> findLessonsByType(@Param("lessonType") boolean lessonType);
}