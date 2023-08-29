package tw.tutorlink.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.Lessons;
import tw.tutorlink.repository.LessonsDAO;

@Service
public class LessonsService {

	@Autowired
	private LessonsDAO lDAO;
	
	
//	public LessonsService(LessonsDAO lDAO) {
//		this.lDAO = lDAO;
//	}
	
	//查詢全部課程資料
	public List<Lessons> getAllLessons(){
		return lDAO.findAll();
	}
	
	//靠課程名稱查詢課程資料
	public List<Lessons> getLessonsByName(String lessonName){
		return lDAO.findByLessonNameContaining(lessonName);
	}
	
	//靠課程ID查詢資料
	public Optional<Lessons> findLessonsById(int lessonId){
		return lDAO.findById(lessonId);
	}
	
	//新增課程
	public Lessons insertLesson(Lessons lesson) {
		return lDAO.save(lesson);
	}
	
	//修改課程
//	public Lessons updateLesson(Lessons lesson) {
//		Optional<Lessons> lessons = lDAO.findById(lesson.getLessonId());
//		if(lessons.isPresent()) {
//		Lessons result = lessons.get();
//			result.setSubject(lesson.getSubject());
//			result.setLessonName(lesson.getLessonName());
//			result.setLessonType(lesson.getLessonType());
//			result.setImage(lesson.getImage());
//			
//			return lDAO.save(result);
//		}
//		
//		return null;
//	}
	
	//刪除課程
	public void deleteLesson(int lessonId) {
		lDAO.deleteById(lessonId);
	}
}