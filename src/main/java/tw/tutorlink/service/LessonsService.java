package tw.tutorlink.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import tw.tutorlink.bean.LessonDetail;
import tw.tutorlink.bean.Lessons;
import tw.tutorlink.bean.Users;
import tw.tutorlink.repository.LessonDetailDAO;
import tw.tutorlink.repository.LessonsDAO;
import tw.tutorlink.repository.UsersDAO;

@Service
public class LessonsService {

	@Autowired
	private LessonsDAO lDAO;
	
	@Autowired
	private UsersDAO uDAO;
	
	@Autowired
	private LessonDetailDAO ldDAO;
	
	
//	public LessonsService(LessonsDAO lDAO) {
//		this.lDAO = lDAO;
//	}
	
	//查詢全部課程資料
	public List<Lessons> getAllLessons(){
		return lDAO.findAll();
	}
	//靠UserID查這位User全部課程資料
	public List<Lessons> getUserAllLessons(HttpSession session){
		Users loggedInUser = (Users) session.getAttribute("logState");
		return loggedInUser.getLesson();
	}
	
	//靠課程ID查詢資料
	public Lessons findLessonsById(Lessons lesson){
		Optional<Lessons> lessons = lDAO.findById(lesson.getLessonId());
		if(lessons.isPresent()) {
			return lessons.get();
		}
		return null;
	}
	
	//新增課程

	public Lessons insertLesson(Lessons lesson) {
		
		return lDAO.save(lesson);
	
	}

	public Lessons insertLesson(int id,Lessons lesson,LessonDetail lessonDetail) {
		Users user = uDAO.findById(id);
		Lessons lessons = new Lessons();
		System.out.println("會員ID : "+user);
		if(user!=null) {
			lessons.setUsers(user);
			lessons.setLessonName(lesson.getLessonName());
			lessons.setSubject(lesson.getSubject());
			lessons.setLessonType(lesson.getLessonType());
			lessons.setPrice(lesson.getPrice());
			lessons.setImage(lesson.getImage());
			

			LessonDetail lD = new LessonDetail();
			lD.setImformation(lessonDetail.getImformation());
			lD.setMeetingUrl(lessonDetail.getMeetingUrl());
			lD.setCreateTime(lessonDetail.getCreateTime());
			lD.setCourseTotalTime(lessonDetail.getCourseTotalTime());
			lD.setCourseUrl(lessonDetail.getCourseUrl());
			lessons.setLessondetail(lD);
			
			List<Lessons> lessonList = new ArrayList<>();
			lessonList.add(lessons);
			user.setLesson(lessonList);
			uDAO.save(user);

		}

			

		System.out.println("課程資料 : "+lessons);
		return lessons;


	}
	
	//修改課程
	public Lessons updateLesson(Lessons lesson) {
		Optional<Lessons> lessons = lDAO.findById(lesson.getLessonId());
		if(lessons.isPresent()) {
		Lessons result = lessons.get();
			result.setSubject(lesson.getSubject());
			result.setLessonName(lesson.getLessonName());
			result.setLessonType(lesson.getLessonType());
			result.setImage(lesson.getImage());
			
			return lDAO.save(result);
		}
		
		return null;
	}
	
	//刪除課程
	public String deleteLessons(Lessons lesson) {
		Optional<Lessons> lessons = lDAO.findById(lesson.getLessonId());
		if(lessons.isPresent()) {
			lDAO.deleteById(lesson.getLessonId());
			return "刪除成功";
		}
		return "刪除失敗";
	}
	
	//用User找課程
	public List<Lessons> findByUsers(Users user){
		return lDAO.findByUsers(user);
	}
}