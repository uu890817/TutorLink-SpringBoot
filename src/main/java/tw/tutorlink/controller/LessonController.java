package tw.tutorlink.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import tw.tutorlink.bean.LessonDetail;
import tw.tutorlink.bean.Lessons;
import tw.tutorlink.bean.Subject;
import tw.tutorlink.bean.Users;

import tw.tutorlink.service.LessonDetailService;
import tw.tutorlink.service.LessonsService;
import tw.tutorlink.service.SubjectService;
import tw.tutorlink.service.VideoNoteService;

@RestController
public class LessonController {
	
	@Autowired
	private SubjectService sService;
	
	@Autowired
	private LessonsService lService;
	
	@Autowired
	private LessonDetailService ldService;
	
	@Autowired
	private VideoNoteService vnService; 
	
	
	
	//------------------ 課程類別 ---------------------
	
	//課程類別新增
	@PostMapping(path="/subjects",produces="application/json;charset=UTF-8")
	public Subject insertSubject(@RequestBody Subject subject) {
		return sService.insertSubject(subject);
	}
	
	//課程類別全部查詢
	@GetMapping(path="/allSubjects",produces="application/json;charset=UTF-8")
	public List<Subject> findAllSubjects(){
		return sService.getAllSubject();
	}
	
	//課程類別單筆查詢
	@GetMapping(path="/findSubjects",produces="application/json;charset=UTF-8")
	public Subject findSubjectById(@RequestBody Subject subject) {
		return sService.findSubjectById(subject);
	}
	
	//課程類別修改
	@PutMapping(path="/updateSubjects",produces="application/json;charset=UTF-8")
	public Subject updateSubject(@RequestBody Subject subject) {
		return sService.updateSubject(subject);
	}
	
	//課程類別刪除
	@DeleteMapping(path="/deleteSubject",produces="application/json;charset=UTF-8")
	public String deleteSubject(@RequestBody Subject subject) {
		return sService.deleteSubject(subject);
	}
	
	//------------------ 課程 -----------------------
	
	//課程新增
	@PostMapping(path="/lessons",produces="application/json;charset=UTF-8")
	public Lessons insertLesson(@RequestBody Lessons lesson,HttpSession session) {
		Users loggedInUser = (Users) session.getAttribute("logState");
		System.out.println("前端資料 : "+loggedInUser);
		return lService.insertLesson(loggedInUser.getUsersId(),lesson);
	}
	
	//課程全部查詢
	@GetMapping(path="/allLessons",produces="application/json;charset=UTF-8")
	public List<Lessons> findAllLessons(HttpSession session) {
		return lService.getUserAllLessons(session);
	}
	
	//課程單筆查詢
	@GetMapping(path="/findLessons",produces="application/json;charset=UTF-8")
	public Lessons findLesson(@RequestBody Lessons lesson) {
		return lService.findLessonsById(lesson);
	}
	
	//課程修改
	@PutMapping(path="/updateLessons",produces="application/json;charset=UTF-8")
	public Lessons updateLesson(@RequestBody Lessons lesson) {
		return lService.updateLesson(lesson);
	}
	
	//課程刪除
	@DeleteMapping(path="/deleteLessons",produces="application/json;charset=UTF-8")
	public String deleteLessons(@RequestBody Lessons lesson) {
		return lService.deleteLessons(lesson);
	}
	
	//------------------ 課程明細 -----------------------
	
	//課程明細新增
	@PostMapping(path="/lessonDetail",produces="application/json;charset=UTF-8")
	public LessonDetail insertLessonDetail(@RequestBody LessonDetail lessonDetail) {
		return ldService.insertLessonDetail(lessonDetail);
	}
	
	//課程明細單筆查詢
	@GetMapping(path="/findLessonDetail",produces="application/json;charset=UTF-8")
	public LessonDetail findLessonDetail(@RequestBody LessonDetail lessonDetail) {
		return ldService.findLessonDetailById(lessonDetail);
	}
	
	//課程明細修改
	@PutMapping(path="/updateLessonDetail",produces="application/json;charset=UTF-8")
	public LessonDetail updateLessonDetail(@RequestBody LessonDetail lessonDetail) {
		return ldService.updateLessonDetail(lessonDetail);
	}
	
	
}	
