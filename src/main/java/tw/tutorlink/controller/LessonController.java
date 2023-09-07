package tw.tutorlink.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.grpc.stub.annotations.RpcMethod;
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
	public ResponseEntity<Lessons> insertLesson(HttpSession session,@RequestParam("lessonName")String lessonName,@RequestParam("subject")Subject subject,
			@RequestParam("lessonType")boolean lessonType,@RequestParam(name="image",required = false)MultipartFile image,@RequestParam("price")Integer price,
			@RequestParam(name="imformation",defaultValue="")String imformation,@RequestParam(name="meetingURL",defaultValue="")String meetingUrl,
			@RequestParam(name="video",required = false)MultipartFile courseUrl,@RequestParam(name="createTime",required = false)Date createTime,@RequestParam(name="courseTotalTime",defaultValue="")Integer courseTotalTime,
			@RequestParam(name="language",defaultValue="")String language) {
try {
		Users loggedInUser = (Users) session.getAttribute("logState");
		System.out.println("前端資料 : "+loggedInUser);
		Lessons lesson = null;
		LessonDetail LD = null;
		
		if (!image.isEmpty()) {
            // 保存文件到本地文件夾
            String imageFileName = generateUniqueFileName(image.getOriginalFilename());
            String savePath = "c:/temp/upload/image";
            File saveFile = new File(savePath + imageFileName);
            image.transferTo(saveFile);
            System.out.println("圖片已存入本地資料夾");
            // 获取图像保存路径
            String imageSavePath = saveFile.getAbsolutePath();
            System.out.println(lessonName+" "+lessonType+" "+image+" ");
            lesson = new Lessons(lessonName, subject, lessonType, imageSavePath, price);
		}
		if (courseUrl == null || courseUrl.isEmpty()) {
		    LD = new LessonDetail(imformation, meetingUrl, "", createTime, courseTotalTime,language);
		} else {
		    String videoFileName = generateUniqueFileName(courseUrl.getOriginalFilename());
		    String savePath = "c:/temp/upload/video";
		    File saveFile = new File(savePath + videoFileName);
		    courseUrl.transferTo(saveFile);
		    String videoSavePath = saveFile.getAbsolutePath();
		    
		    LD = new LessonDetail(imformation, meetingUrl, videoSavePath, createTime, courseTotalTime,language);
		}


            Lessons savedLesson = lService.insertLesson(loggedInUser.getUsersId(),lesson,LD);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedLesson);
	
	}catch(Exception e) {
		e.printStackTrace();
		  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
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
	
	//生成一個唯一的文件名，避免文件名衝突
    private String generateUniqueFileName(String originalFileName) {
        String baseName = FilenameUtils.getBaseName(originalFileName);
        String extension = FilenameUtils.getExtension(originalFileName);
        String uniqueName = baseName + "-" + UUID.randomUUID().toString() + "." + extension;
        return uniqueName;
    }
	
	
	
	
}	
