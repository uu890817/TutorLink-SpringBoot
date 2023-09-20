package tw.tutorlink.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import tw.tutorlink.bean.Video;
import tw.tutorlink.dto.lessons.LessonsUpdateDTO;
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

	// ------------------ 課程類別 ---------------------

	// 課程類別新增
	@PostMapping(path = "/subjects", produces = "application/json;charset=UTF-8")
	public Subject insertSubject(@RequestBody Subject subject) {
		return sService.insertSubject(subject);
	}

	// 課程類別全部查詢
	@GetMapping(path = "/allSubjects", produces = "application/json;charset=UTF-8")
	public List<Subject> findAllSubjects() {
		return sService.getAllSubject();
	}

	// 課程類別單筆查詢
	@GetMapping(path = "/findSubjects", produces = "application/json;charset=UTF-8")
	public Subject findSubjectById(@RequestBody Subject subject) {
		return sService.findSubjectById(subject);
	}

	// 課程類別修改
	@PutMapping(path = "/updateSubjects", produces = "application/json;charset=UTF-8")
	public Subject updateSubject(@RequestBody Subject subject) {
		return sService.updateSubject(subject);
	}

	// 課程類別刪除
	@DeleteMapping(path = "/deleteSubject", produces = "application/json;charset=UTF-8")
	public String deleteSubject(@RequestBody Subject subject) {
		return sService.deleteSubject(subject);
	}

	// ------------------ 課程 -----------------------

	// 課程新增
	@PostMapping(path = "/lessons", produces = "application/json;charset=UTF-8")
	public ResponseEntity<Integer> insertLesson(HttpSession session, @RequestParam("lessonName") String lessonName,
			@RequestParam("subject") Subject subject, @RequestParam("lessonType") boolean lessonType,
			@RequestParam(name = "image", required = false) MultipartFile image, @RequestParam("price") Integer price,
			@RequestParam(name = "information", defaultValue = "") String information,
			@RequestParam(name = "meetingURL", defaultValue = "") String meetingUrl,
			@RequestParam(name = "video", required = false) MultipartFile courseUrl,
			@RequestParam(name = "createTime", required = false) Date createTime,
			@RequestParam(name = "courseTotalTime", defaultValue = "") Integer courseTotalTime,
			@RequestParam(name = "language", defaultValue = "") String language) {
		try {
			Users loggedInUser = (Users) session.getAttribute("logState");
			System.out.println("前端資料 : " + loggedInUser);
			Lessons lesson = null;
			LessonDetail LD = null;

			if (!image.isEmpty()) {
				// 保存文件到本地文件夾
				String imageFileName = generateUniqueFileName(image.getOriginalFilename());
				String savePath = "c:/temp/upload/image/";
				File saveFile = new File(savePath + imageFileName);
				image.transferTo(saveFile);
				System.out.println("圖片已存入本地資料夾");
				// 获取图像保存路径
				String imageSavePath = saveFile.getAbsolutePath();
				System.out.println(lessonName + " " + lessonType + " " + image + " ");
//				lesson = new Lessons(lessonName, subject, lessonType, imageSavePath, price);
				//存圖片名稱
				lesson = new Lessons(lessonName, subject, lessonType, imageFileName, price);
			}
			if (courseUrl == null || courseUrl.isEmpty()) {
				LD = new LessonDetail(information, meetingUrl, "", createTime, courseTotalTime, language);
			} else {
				String videoFileName = generateUniqueFileName(courseUrl.getOriginalFilename());
				String savePath = "c:/temp/upload/video/";
				File saveFile = new File(savePath + videoFileName);
				courseUrl.transferTo(saveFile);
				String videoSavePath = saveFile.getAbsolutePath();

//				LD = new LessonDetail(information, meetingUrl, videoSavePath, createTime, courseTotalTime, language);
				//存影片名稱
				LD = new LessonDetail(information, meetingUrl, videoFileName, createTime, courseTotalTime, language);
			}

			Lessons savedLesson = lService.insertLesson(loggedInUser.getUsersId(), lesson, LD);
			// 创建一个包含LessonDetailId的响应JSON对象
//        	Map<String, Object> response = new HashMap<>();
//        	response.put("lessonDetailId", savedLesson.getLessondetail().getLessonDetailId());

			Integer response = savedLesson.getLessondetail().getLessonDetailId();
			return ResponseEntity.status(HttpStatus.CREATED).body(response);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// 單一使用者課程全部查詢
	@GetMapping(path = "/allLessons", produces = "application/json;charset=UTF-8")
	public List<Lessons> findAllLessons(HttpSession session) {
		Users loggedInUser = (Users) session.getAttribute("logState");
		boolean lesson = true;
		return lService.getUserAllLessons(loggedInUser.getUsersId(), lesson);
	}

	// 課程單筆查詢
	@PostMapping(path = "/findLessons/{lessonId}", produces = "application/json;charset=UTF-8")
	public Lessons findLesson(@PathVariable("lessonId")Integer id) {
		Lessons lesson = lService.findByLessonId(id).get();
		String savePath = "c:/temp/upload/image/";
		String imagePath = savePath+lesson.getImage();
		System.out.println(imagePath);
		try {
			
			byte[] fileBytes = readFileToByteArray(imagePath);

	        // 將檔案內容轉換為Base64
	        String base64Image = Base64.getEncoder().encodeToString(fileBytes);

	        lesson.setImage(base64Image);
		}catch(IOException e){
			e.printStackTrace();
			
		}
		return lesson;
	}
	//靠LessonType來決定顯示哪個的所有課程
	@GetMapping(path="/findlesson/{lessonType}", produces = "application/json;charset=UTF-8")
	public List<Lessons> findLessonByLessonType(@PathVariable("lessonType")boolean lessonType) {
		if(lessonType) {
			return lService.findOnlineLessonsByType();
		}
		    return lService.findVideoLessonsByType();
		
	}
	
	
	

	   // 課程修改
		@PutMapping(path = "/updateLessons/{lessonId}", produces = "application/json;charset=UTF-8")
		public ResponseEntity<Integer> updateLesson(@PathVariable("lessonId") Integer id,
				@RequestParam(name = "lessonName", required = false) String lessonName,
				@RequestParam(name = "image", required = false) MultipartFile image,
				@RequestParam(name = "price", required = false) Integer price,
				@RequestParam(name = "information", defaultValue = "") String information,
				@RequestParam(name = "meetingURL", defaultValue = "") String meetingUrl,
				@RequestParam(name = "video", required = false) MultipartFile courseUrl,
				@RequestParam(name = "courseTotalTime", defaultValue = "") Integer courseTotalTime,
				@RequestParam(name = "language", defaultValue = "") String language,
				@RequestParam("subject") Subject subject) {

			try {

				Lessons lesson = lService.findByLessonId(id).get();
				LessonDetail LD = ldService.findLessonDetailByLessonId(id);
				LessonsUpdateDTO lDTO = new LessonsUpdateDTO();

				if (!image.isEmpty()) {
					// 保存文件到本地文件夾
					String imageFileName = generateUniqueFileName(image.getOriginalFilename());
					String savePath = "c:/temp/upload/image/";
					File saveFile = new File(savePath + imageFileName);
					image.transferTo(saveFile);
					System.out.println("圖片已存入本地資料夾");
					// 获取图像保存路径
					String imageSavePath = saveFile.getAbsolutePath();
					System.out.println(lessonName + " " + image + " ");

//					lDTO.setImage(imageSavePath);
					lDTO.setImage(imageFileName);
					lDTO.setLessonName(lessonName);
					lDTO.setPrice(price);
					lDTO.setSubject(subject);

				}

				if (courseUrl == null || courseUrl.isEmpty()) {
					LD = new LessonDetail(information, meetingUrl, language, null, courseTotalTime, language);
				} else {
					String videoFileName = generateUniqueFileName(courseUrl.getOriginalFilename());
					String savePath = "c:/temp/upload/video/";
					File saveFile = new File(savePath + videoFileName);
					courseUrl.transferTo(saveFile);
					String videoSavePath = saveFile.getAbsolutePath();

//					LD = new LessonDetail(information, meetingUrl, videoSavePath, null, courseTotalTime, language);
					LD = new LessonDetail(information, meetingUrl, videoFileName, null, courseTotalTime, language);
				}
				System.out.println("我要看的lesson :" + lesson.getSubject());
				Lessons savedLesson = lService.updateLesson(id, lDTO, LD);
				Integer response = savedLesson.getLessondetail().getLessonDetailId();
				return ResponseEntity.status(HttpStatus.CREATED).body(response);

			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}

		}
	
	

	// 課程刪除
	@DeleteMapping(path = "/deleteLessons", produces = "application/json;charset=UTF-8")
	public String deleteLessons(@RequestBody Lessons lesson) {
		System.out.println("lessonID " + lesson.getLessonId());
		return lService.deleteLessons(lesson);
	}

	// ------------------ 課程明細 -----------------------

	// 課程明細新增
	@PostMapping(path = "/lessonDetail", produces = "application/json;charset=UTF-8")
	public LessonDetail insertLessonDetail(@RequestBody LessonDetail lessonDetail) {
		return ldService.insertLessonDetail(lessonDetail);
	}

	// 課程明細單筆查詢
	@GetMapping(path = "/findLessonDetail", produces = "application/json;charset=UTF-8")
	public LessonDetail findLessonDetail(@RequestBody LessonDetail lessonDetail) {
		return ldService.findLessonDetailById(lessonDetail);
	}

	// 靠課程ID查詢課程明細
	@GetMapping(path = "/findLessonDetailByLessonId", produces = "application/json;charset=UTF-8")
	public LessonDetail findLessonDetail(@RequestParam("lessonId") int id) {
		return ldService.findLessonDetailByLessonId(id);
	}

	// 課程明細修改
	@PutMapping(path = "/updateLessonDetail", produces = "application/json;charset=UTF-8")
	public LessonDetail updateLessonDetail(@RequestParam("id") int id, @RequestBody LessonDetail lessonDetail) {
		return ldService.updateLessonDetail(id, lessonDetail);
	}

	// 生成一個唯一的文件名，避免文件名衝突
	private String generateUniqueFileName(String originalFileName) {
		String baseName = FilenameUtils.getBaseName(originalFileName);
		String extension = FilenameUtils.getExtension(originalFileName);
		String uniqueName = baseName + "-" + UUID.randomUUID().toString() + "." + extension;
		return uniqueName;
	}
	//讀取檔案內容並返回位元組陣列
	private byte[] readFileToByteArray(String filePath) throws IOException {
	    File file = new File(filePath);
	    FileInputStream fis = new FileInputStream(file);
	    byte[] fileBytes = new byte[(int) file.length()];
	    fis.read(fileBytes);
	    fis.close();
	    return fileBytes;
	}
	

}
