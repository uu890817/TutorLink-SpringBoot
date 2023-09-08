package tw.tutorlink.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;
import tw.tutorlink.bean.CourseQA;
import tw.tutorlink.bean.LessonDetail;
import tw.tutorlink.bean.LessonPost;
import tw.tutorlink.bean.Lessons;
import tw.tutorlink.bean.Subject;
import tw.tutorlink.bean.Users;
import tw.tutorlink.bean.Video;
import tw.tutorlink.bean.VideoNote;
import tw.tutorlink.bean.VideoUploadDTO;
import tw.tutorlink.service.CourseQAService;
import tw.tutorlink.service.LessonDetailService;
import tw.tutorlink.service.LessonPostService;
import tw.tutorlink.service.LessonsService;
import tw.tutorlink.service.VideoNoteService;
import tw.tutorlink.service.VideoService;

import org.apache.commons.io.FilenameUtils;
import java.util.UUID;




@RestController
public class VideoCourseController {

	@Autowired
	private VideoService vService;
	
	@Autowired
	private LessonsService lService;
	
	@Autowired
	private LessonDetailService ldService;
	
	@Autowired
	private CourseQAService qaService;
	
	@Autowired
	private LessonPostService lpService;
	
	@Autowired
	private VideoNoteService vnService;
	
	//-------------------課程-------------------
	//新增影片課程
//	@PostMapping(path = "/VideoLesson", produces = "application/json;charset=UTF-8")
//	public ResponseEntity<Lessons> createVideoLesson(@RequestParam("lessonName") String lessonName,
//			@RequestParam("subject") Subject subject, @RequestParam("lessonType") boolean lessonType,
//			@RequestParam("image") MultipartFile image, @RequestParam("price") Integer price, HttpSession session) {
//		try {
//			Users loggedInUser = (Users) session.getAttribute("logState");
//			Lessons lesson = null; // 初始化 lesson 变量
//			
//			if (!image.isEmpty()) {
//				// 保存文件到本地文件夾
//				String fileName = generateUniqueFileName(image.getOriginalFilename());
//				String savePath = "c:/temp/upload/";
//				File saveFile = new File(savePath + fileName);
//				image.transferTo(saveFile);
//				// 获取图像保存路径
//				String imageSavePath = saveFile.getAbsolutePath();
//				
//				System.out.println("lessonName:"+lessonName+" subject:"+subject.getSubjectContent()+ " lessonType:"+lessonType+" imageSavePath:"+imageSavePath+" price:"+price);
//				lesson = new Lessons(lessonName, subject, lessonType, imageSavePath, price);
//			}
//			Lessons savedLesson = lService.insertLesson(loggedInUser.getUsersId(), lesson);
//			return ResponseEntity.status(HttpStatus.CREATED).body(savedLesson);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//		}
//	}
	//新增課程Detail
	@PostMapping(path="/VideoLessonDetail",produces="application/json;charset=UTF-8")
	public ResponseEntity<LessonDetail> createVideoLessonDetail(@RequestParam("lesson") Lessons lesson,
			@RequestParam("information") String Information, @RequestParam("createTime") Date CreateTime,
			@RequestParam("video") MultipartFile video, @RequestParam("language") String language, 
			@RequestParam("courseTotalTime") Integer courseTotalTime) {
		try {
			LessonDetail lessonDetail = null;
			if (!video.isEmpty()) {
				// 保存文件到本地文件夾
				String fileName = generateUniqueFileName(video.getOriginalFilename());
				String savePath = "c:/temp/upload/";
				File saveFile = new File(savePath + fileName);
				video.transferTo(saveFile);
				// 獲取圖片保存路徑
				String imageSavePath = saveFile.getAbsolutePath();
				
				lessonDetail = new LessonDetail(lesson, Information, CreateTime, imageSavePath, language,courseTotalTime);
			}
		LessonDetail savedLessonDetail = ldService.insertLessonDetail(lessonDetail);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedLessonDetail);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	
	//取得老師發佈的課程
	@GetMapping(path="/VideoLessons",produces="application/json;charset=UTF-8")
	public List<Lessons> getLessonsByTeacher(HttpSession session){
		Users loggedInUser = (Users) session.getAttribute("logState");
		return lService.findByUsers(loggedInUser);
	}
	
	
	//-----------------影片--------------------
	
	//新增影片
	@PostMapping(path="/addVideo",produces="application/json;charset=UTF-8")
	public Video addVideo(@RequestBody Video video) {
		return vService.insertVideo(video);
	}
	
//	//更新課程影片
//	@PostMapping(path="/uploadVideo",produces="application/json;charset=UTF-8")
//    public ResponseEntity<String> uploadVideo(
//            @RequestParam("title") String title,
//            @RequestParam("videoFile") MultipartFile file) {
//        try {
//        	vService.uploadVideo(title, file);
//            return ResponseEntity.ok("影片已成功上傳！");
//        } catch (IOException e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("影片上傳失敗！");
//        }
//    }
	
	//刪除單筆影片
	@DeleteMapping(path="/deleteVideo/{videoId}",produces="application/json;charset=UTF-8")
	public void deleteVideo(@PathVariable Integer videoId) {
		vService.deleteVideo(videoId);
	}
	
	//刪除課程的所有影片
	@DeleteMapping(path="/deleteVideoByCourse",produces="application/json;charset=UTF-8")
	public List<Video> deleteVideoByCourse(@RequestBody Integer lessonDetailId) {
		return vService.deleteVideoByLesson(lessonDetailId);
	}
	
	//取得一門課程所有影片
	@GetMapping(path="/findVideoByCourse/{courseId}",produces="application/json;charset=UTF-8")
	public List<Video> findVideoByCourse(@PathVariable Integer courseId) {
		return vService.findVideoByLesson(courseId);
	}
	
	//新增多筆影片
	@PostMapping(path="/uploadVideo",produces="application/json;charset=UTF-8")
	public String uploadVideo(@RequestParam("videoList") List<VideoUploadDTO> videoDTOs) {
		try {
			for (VideoUploadDTO videoDTO : videoDTOs) {
				// 检查文件不為空
				if (videoDTO.getVideoFile() != null && !videoDTO.getVideoFile().isEmpty()) {
					// 生成一个唯一的文件名
					String fileName = generateUniqueFileName( videoDTO.getVideoFile().getOriginalFilename());

					// 保存文件到本地文件夾
					String savePath = "c:/temp/upload/";
					File saveFile = new File(savePath + fileName);
					videoDTO.getVideoFile().transferTo(saveFile);

					// 創建一个Video對象並設置courseUrl為文件的保存路徑
					Video video = new Video();
					video.setLessonDetail(videoDTO.getLessonDetail());
					video.setSort(videoDTO.getSort());
					video.setChapterName(videoDTO.getChapterName());
					video.setCourseUrl(saveFile.getAbsolutePath());

					// 使用videoService保存Video对象到数据库
					vService.insertVideo(video);
				}

			}
			return "影片上傳成功";
		} catch (IOException e) {
			e.printStackTrace();
			return "影片上傳失败";
		}
	}
	
	/////////////////
	@PostMapping(path = "/uploadVideo2", produces = "application/json;charset=UTF-8")
	public String uploadVideo(@RequestParam("chapterName") List<String> chapterNames,
	                          @RequestParam("videoFile") List<MultipartFile> videoFiles,
	                          @RequestParam("lessonDetail") List<LessonDetail> lessonDetailJsons,
	                          @RequestParam("sort") List<Integer> sorts) {
	    try {
	        for (int i = 0; i < chapterNames.size(); i++) {
	            // 获取每个章节的数据
	            String chapterName = chapterNames.get(i);
	            MultipartFile videoFile = videoFiles.get(i);
	            LessonDetail lessonDetailJson = lessonDetailJsons.get(i);
	            Integer sort = sorts.get(i);

	            // 处理章节数据，例如保存文件并插入数据库
	            if (videoFile != null && !videoFile.isEmpty()) {
	                String fileName = generateUniqueFileName(videoFile.getOriginalFilename());
	                String savePath = "c:/temp/upload/";
	                File saveFile = new File(savePath + fileName);
	                videoFile.transferTo(saveFile);

	                // 解析 lessonDetailJson 字符串为 LessonDetail 对象
//	                LessonDetail lessonDetail = objectMapper.readValue(lessonDetailJson, LessonDetail.class);

	                // 创建 Video 对象并设置属性
	                Video video = new Video();
	                video.setLessonDetail(lessonDetailJson);
	                video.setSort(sort);
	                video.setChapterName(chapterName);
	                video.setCourseUrl(saveFile.getAbsolutePath());

	                // 使用 videoService 保存 Video 对象到数据库
	                vService.insertVideo(video);
	            }
	        }
	        return "影片上傳成功";
	    } catch (IOException e) {
	        e.printStackTrace();
	        return "影片上傳失败";
	    }
	}
	
//	@PostMapping(path="/uploadOneVideo",produces="application/json;charset=UTF-8")
//	public Video insertVideo(@RequestParam("videoFile")MultipartFile videoFile,
//			@RequestParam("chapterName")String chapterName,@RequestParam("sort")Integer sort,
//			@RequestParam("lessonDetail")LessonDetail lessonDetail) throws IllegalStateException, IOException {
//		Video video = null;
//		if (videoFile != null && !videoFile.isEmpty()) {
//            String fileName = generateUniqueFileName(videoFile.getOriginalFilename());
//            String savePath = "c:/temp/upload/";
//            File saveFile = new File(savePath + fileName);
//            videoFile.transferTo(saveFile);
//            System.out.println("影片以傳入資料夾");
//
//            // 创建 Video 对象并设置属性
//            video = new Video();
//            video.setLessonDetail(lessonDetail);
//            video.setSort(sort);
//            video.setChapterName(chapterName);
//            video.setCourseUrl(saveFile.getAbsolutePath());
//
//            // 使用 videoService 保存 Video 对象到数据库
//            
//        }
//		return vService.insertVideo(video);
//	}
	
	@PostMapping(path = "/uploadOneVideo", produces = "application/json;charset=UTF-8")
	public ResponseEntity<Video> insertVideo(@RequestParam("videoFile") MultipartFile videoFile,
	        @RequestParam("chapterName") String chapterName, @RequestParam("sort") Integer sort,
	        @RequestParam("lessonDetail") String lessonDetail) throws IllegalStateException, IOException {
	    if (videoFile != null && !videoFile.isEmpty()) {
	        String fileName = generateUniqueFileName(videoFile.getOriginalFilename());
	        String savePath = "c:/temp/upload/";
	        File saveFile = new File(savePath + fileName);
	        videoFile.transferTo(saveFile);
	        System.out.println("影片已傳入資料夾");

	        // 创建 Video 对象并设置属性
	        Video video = new Video();
//	        video.setLessonDetail(lessonDetail);
	        video.setSort(sort);
	        video.setChapterName(chapterName);
	        video.setCourseUrl(saveFile.getAbsolutePath());
	        
	     // 解析 lessonDetail JSON 字符串为 LessonDetail 对象
            ObjectMapper objectMapper = new ObjectMapper();
            LessonDetail lessonDetailObject = objectMapper.readValue(lessonDetail, LessonDetail.class);
            video.setLessonDetail(lessonDetailObject);

	        // 使用 videoService 保存 Video 对象到数据库
	        Video savedVideo = vService.insertVideo(video);

	        return ResponseEntity.ok(savedVideo);
	    } else {
	        // 处理没有上传视频文件的情况
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}



    // 生成一个唯一的文件名，避免文件名衝突
    private String generateUniqueFileName(String originalFileName) {
        String baseName = FilenameUtils.getBaseName(originalFileName);
        String extension = FilenameUtils.getExtension(originalFileName);
        String uniqueName = baseName + "-" + UUID.randomUUID().toString() + "." + extension;
        return uniqueName;
    }
    
    //--------------------QA---------------------------
    
    //取得老師課程的所有問答
    @GetMapping(path="/courseQA/user",produces="application/json;charset=UTF-8")
    public List<CourseQA> getCourseQAByUser(HttpSession session) {
    	Users loggedInUser = (Users) session.getAttribute("logState");
        return qaService.getCourseQAByUser(loggedInUser);
    }
    
    //學生新增提出問題
    @PostMapping(path="/courseQA",produces="application/json;charset=UTF-8")
    public ResponseEntity<CourseQA> createCourseQA(@RequestBody CourseQA courseQA,HttpSession session) {
    	Users loggedInUser = (Users) session.getAttribute("logState");
    	CourseQA savedcourseQA = new CourseQA();
    	savedcourseQA.setUsers(loggedInUser);
    	savedcourseQA.setLesson(courseQA.getLesson());
        savedcourseQA.setTitle(courseQA.getTitle());
        savedcourseQA.setQuestion(courseQA.getQuestion());

        // 執行其他必要的操作，例如設置 Users 和 Lessons，處理時間等等

        // 將新的 CourseQA 對象保存到資料庫
        CourseQA savedCourseQA = qaService.saveCourseQA(courseQA);

        // 返回新的 CourseQA 對象，您可以包含其他信息，例如新增的 ID
        return ResponseEntity.ok(savedCourseQA);
    }
    
    //-------------Post--------------------
    
    //取得老師發佈過的公告
    @GetMapping(path="/coursePost",produces="application/json;charset=UTF-8")
    public List<LessonPost> getPostByUser(HttpSession session) {
    	Users loggedInUser = (Users) session.getAttribute("logState");
    	return lpService.findLessPostByUsers(loggedInUser.getUsersId());
    }
    
    //新增一筆公告
    @PostMapping(path="/coursePost",produces="application/json;charset=UTF-8")
    public LessonPost insertPost(@RequestBody LessonPost lessonPost) {
    	return lpService.createLessonPost(lessonPost);
    }
    
    //刪除一筆公告
    @DeleteMapping(path="/coursePost/{lessonPostId}")
    public void deletePost(@PathVariable Integer lessonPostId) {
    	lpService.deleteLessonPost(lessonPostId);
    }
    
    //-------------Note---------------------
    
    //取得使用者在這部影片的筆記
    @GetMapping(path="/videoNote",produces="application/json;charset=UTF-8")
    public List<VideoNote> getNoteByUserVideo(Integer videoId,HttpSession session){
    	Users loggedInUser = (Users) session.getAttribute("logState");
    	return vnService.findByLessonAndUser(videoId, loggedInUser.getUsersId());
    }
    
    //新增筆記
    @PostMapping(path="/videoNote",produces="application/json;charset=UTF-8")
    public VideoNote inserVideoNote(@RequestBody VideoNote videoNote) {
    	return vnService.createVideoNote(videoNote);
    }
    
    //刪除筆記
    @DeleteMapping(path="/videoNote/{videoNoteId}",produces="application/json;charset=UTF-8")
    public void deleteVideoNote(@PathVariable Integer videoNoteId) {
    	vnService.deleteVideoNote(videoNoteId);
    }
    
}
