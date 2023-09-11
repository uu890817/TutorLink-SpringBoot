package tw.tutorlink.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.tutorlink.bean.Video;

public interface VideoDAO extends JpaRepository<Video, Integer> {

	//查詢此課程所有影片
	List<Video> findByLessonDetail_LessonDetailId(Integer lessonDetailId);
	
	//刪除此課程所有影片
	List<Video> deleteByLessonDetail_LessonDetailId(Integer lessonDetailId);
	
}
