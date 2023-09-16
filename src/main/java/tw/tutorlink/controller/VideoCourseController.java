package tw.tutorlink.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;
import tw.tutorlink.bean.CourseQA;
import tw.tutorlink.bean.LessonDetail;
import tw.tutorlink.bean.LessonPost;
import tw.tutorlink.bean.Lessons;
import tw.tutorlink.bean.OrderItem;
import tw.tutorlink.bean.StudentWillLearn;
import tw.tutorlink.bean.Subject;
import tw.tutorlink.bean.UserDetail;
import tw.tutorlink.bean.Users;
import tw.tutorlink.bean.Video;
import tw.tutorlink.bean.VideoCourseDTO;
import tw.tutorlink.bean.VideoNote;
import tw.tutorlink.service.CourseQAService;
import tw.tutorlink.service.LessonDetailService;
import tw.tutorlink.service.LessonPostService;
import tw.tutorlink.service.LessonsService;
import tw.tutorlink.service.OrderItemService;
import tw.tutorlink.service.StudentWillLearnService;
import tw.tutorlink.service.UserDetailService;
import tw.tutorlink.service.UsersService;
import tw.tutorlink.service.VideoNoteService;
import tw.tutorlink.service.VideoService;

import org.apache.commons.io.FilenameUtils;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;




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
	
	@Autowired
	private StudentWillLearnService swlService;
	
	@Autowired
	private OrderItemService oiService;
	
	@Autowired
	private UsersService uService;
	
	@Autowired
	private UserDetailService udService;
	
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
	
	//新增willLearn
	@PostMapping(path="/willLearn")
	public List<StudentWillLearn> insertWillLearn(@RequestBody Map<String, List<String>> requestBody,@RequestParam("id")Integer lessonId) {
		System.out.println(requestBody); 
		List<String> willLearnList = requestBody.get("willLearnList");
//		String[] willLearnArray = willLearnList.split(","); // 使用逗号作为分隔符，可以根据实际情况修改
//
		List<StudentWillLearn> insertedWillLearnList = new ArrayList<>();
		    
		    for (String willLearn : willLearnList) {
		        StudentWillLearn studentWillLearn = swlService.insertStudentWillLearn(willLearn, lessonId);
		        insertedWillLearnList.add(studentWillLearn);
		    }

		    return insertedWillLearnList;
		
	}
	
	//取得這門課程for課名
	@GetMapping(path="/findLessonByLessonId/{lessonId}",produces="application/json;charset=UTF-8")
	public Lessons getLessonByLessonId(@PathVariable("lessonId") Integer lessonId) {
		Lessons lesson = lService.findByLessonId(lessonId).get();
		return lesson;
	}
	
	
	//取得老師發佈的課程
	@GetMapping(path="/VideoLessons",produces="application/json;charset=UTF-8")
	public List<Lessons> getLessonsByTeacher(HttpSession session){
		Users loggedInUser = (Users) session.getAttribute("logState");
		int userId = loggedInUser.getUsersId();
		boolean lesson = false;
		return lService.findByUserIdAndLessonType(userId,lesson);
	}
	
	//取得學生購買的課程
	@GetMapping(path="/studentVideoLesson",produces="application/json;charset=UTF-8")
	public List<Lessons> getLessonByStudent(HttpSession session){
		Users loggedInUser = (Users) session.getAttribute("logState");
		int userId = loggedInUser.getUsersId();
//		return oiService.findByUserId(userId);
		return null;
	}
	
	//課程找講師資訊
	@GetMapping(path="/teacherInfo/{lessonId}",produces="application/json;charset=UTF-8")
	public UserDetail findTeacherByLesson(@PathVariable("lessonId") Integer lessonId) {
		return lService.findUserByLessonId(lessonId);
	}
	
	//刪除課程
	@DeleteMapping(path="/delVideoLessons/{lessonId}",produces="application/json;charset=UTF-8")
	public void deleteVideoLesson(@PathVariable("lessonId") Integer lessonId) {
		lService.deleteLessonById(lessonId);
	}

	//用科目找影片課程
	@GetMapping(path="/findVideoLessonsBySub/{subid}",produces="application/json;charset=UTF-8")
	public List<Lessons> findByVideoLessonBySub(@PathVariable("subid") Integer subject){
		return lService.findLessonsBySubIdAndType(subject,false);
	}
	
	//用科目找線上課程
	@GetMapping(path = "/findOnlineLessonsBySub/{subid}", produces = "application/json;charset=UTF-8")
	public List<Lessons> findByOnlineLessonBySub(@PathVariable("subid") Integer subject) {
		return lService.findLessonsBySubIdAndType(subject,true);
	}
	
	//找全部課程
	@GetMapping(path="/findAllLesson",produces="application/json;charset=UTF-8")
	public List<Lessons> findAllLesson(){
		return lService.getAllLessons();
	}
	
	//找全部影片課程
	@GetMapping(path="/findVideoLesson",produces="application/json;charset=UTF-8")
	public List<Lessons> findVideoLesson() {
		return lService.findVideoLessonsByType();
	}
	
	//找全部線上課程
	@GetMapping(path = "/findOnlineLesson", produces = "application/json;charset=UTF-8")
	public List<Lessons> findOnlineLesson() {
		return lService.findOnlineLessonsByType();
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
	
	//取得課程影片檔
	@GetMapping(path="/findVideoUrl/{courseId}",produces="application/json;charset=UTF-8")
	public List<String> findVideoUrl(@PathVariable("courseId") Integer courseId) {
		List<Video> videos = vService.findVideoByLesson(courseId);
//		List<InputStream> videoInputStreams = getVideoInputStreams(videos);
//		return videoInputStreams;
		List<String> videoUrls = getVideoUrls(videos);
	    return videoUrls;
	}
	
	private List<String> getVideoUrls(List<Video> videos) {
	    return videos.stream().map(video -> video.getCourseUrl()).collect(Collectors.toList());
	}
	
	//取得單一筆影片檔
	@GetMapping(path="/getVideo/{videoid}")
	public ResponseEntity<InputStreamResource> getVideo(@PathVariable("videoid") Integer videoId) {
		InputStream videoInputStream = getVideoInputStreamByVideoId(videoId);
		
		if(videoInputStream != null) {
			InputStreamResource videoResource = new InputStreamResource(videoInputStream);
			return ResponseEntity.ok().contentType(MediaType.parseMediaType("video/mp4")).body(videoResource);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	public InputStream getVideoInputStreamByVideoId(Integer videoid) {
	    // 假设您的视频信息存储在数据库中，videoRepository 是用于访问数据库的 repository
	    Optional<Video> videoOptional = vService.findVideoById(videoid);

	    if (videoOptional.isPresent()) {
	        Video video = videoOptional.get();
	        String videoFilePath = video.getCourseUrl(); // 假设您的 Video 对象包含文件路径信息

	        // 使用您的方法将文件路径转换为 InputStream
	        InputStream videoInputStream = convertLocalFilePathToInputStream(videoFilePath);

	        return videoInputStream;
	    } else {
	        // 处理未找到视频的情况，返回 null 或其他适当的响应
	        return null;
	    }
	}

	public InputStream convertLocalFilePathToInputStream(String localFilePath) {
        try {
            InputStream inputStream = new FileInputStream(localFilePath);
            return inputStream;
        } catch (IOException e) {
            e.printStackTrace();
            return null; 
        }
    }
	
	//取得預覽影片
	@GetMapping(path="/preVideo/{lessonId}")
	public ResponseEntity<InputStreamResource> getPreVideo(@PathVariable("lessonId") Integer lessonId){
		InputStream videoInputStream = getPreVideoInputStreamByLessonId(lessonId);
		if(videoInputStream != null) {
			InputStreamResource videoResource = new InputStreamResource(videoInputStream);
			return ResponseEntity.ok().contentType(MediaType.parseMediaType("video/mp4")).body(videoResource);
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	public InputStream getPreVideoInputStreamByLessonId(Integer lessonId) {
		String videoFilePath = ldService.findPreVideo(lessonId);
		System.out.println(videoFilePath);
		InputStream videoInputStream = convertLocalFilePathToInputStream(videoFilePath);
		return videoInputStream;
	}

	


	
	//新增多筆影片
//	@PostMapping(path="/uploadVideo",produces="application/json;charset=UTF-8")
//	public String uploadVideo(@RequestParam("videoList") List<VideoUploadDTO> videoDTOs) {
//		try {
//			for (VideoUploadDTO videoDTO : videoDTOs) {
//				// 检查文件不為空
//				if (videoDTO.getVideoFile() != null && !videoDTO.getVideoFile().isEmpty()) {
//					// 生成一个唯一的文件名
//					String fileName = generateUniqueFileName( videoDTO.getVideoFile().getOriginalFilename());
//
//					// 保存文件到本地文件夾
//					String savePath = "c:/temp/upload/";
//					File saveFile = new File(savePath + fileName);
//					videoDTO.getVideoFile().transferTo(saveFile);
//
//					// 創建一个Video對象並設置courseUrl為文件的保存路徑
//					Video video = new Video();
//					video.setLessonDetail(videoDTO.getLessonDetail());
//					video.setSort(videoDTO.getSort());
//					video.setChapterName(videoDTO.getChapterName());
//					video.setCourseUrl(saveFile.getAbsolutePath());
//
//					// 使用videoService保存Video对象到数据库
//					vService.insertVideo(video);
//				}
//
//			}
//			return "影片上傳成功";
//		} catch (IOException e) {
//			e.printStackTrace();
//			return "影片上傳失败";
//		}
//	}
	
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
	
	//目前新增影片Controller
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
    
    //取得課程問答
    @GetMapping(path="/courseQA/{lessonsId}",produces="application/json;charset=UTF-8")
    public List<CourseQA> getCourseQAByCourse(@PathVariable("lessonsId") Integer lessonsId){
    	return qaService.getCourseQAByLessonId(lessonsId);
    }
    
    //學生新增提出問題
    @PostMapping(path="/courseQA/{lessonId}",produces="application/json;charset=UTF-8")
    public ResponseEntity<CourseQA> createCourseQA(@PathVariable("lessonId") Integer lessonId,@RequestBody CourseQA courseQA,HttpSession session) {
    	Users user = new Users();
    	Users loggedInUser = (Users) session.getAttribute("logState");
    	user.setUsersId(loggedInUser.getUsersId());
    	Lessons lesson = new Lessons();
    	lesson.setLessonId(lessonId);
    	CourseQA savedcourseQA = new CourseQA();
    	savedcourseQA.setUsers(user);
    	savedcourseQA.setLesson(lesson);
        savedcourseQA.setTitle(courseQA.getTitle());
        savedcourseQA.setQuestion(courseQA.getQuestion());
        savedcourseQA.setTime(courseQA.getTime());

        CourseQA savedCourseQA = qaService.saveCourseQA(savedcourseQA);

        return ResponseEntity.ok(savedCourseQA);
    }
    
    //老師回答問題
    @PutMapping(path="/courseQA/{qaId}",produces="application/json;charset=UTF-8")
    public ResponseEntity<CourseQA> addAnswer(@PathVariable("qaId") Integer qaId, @RequestBody CourseQA updatedQA){
    	CourseQA existingQA = qaService.getCourseQAById(qaId);
    	
    	if (existingQA == null) {
            return ResponseEntity.notFound().build();
        }
    	
    	existingQA.setAnswer(updatedQA.getAnswer());
    	CourseQA updatedRecord = qaService.saveCourseQA(existingQA);
    	return ResponseEntity.ok(updatedRecord);
    }
    
    
    //-------------Post--------------------
    
    //取得老師發佈過的公告
    @GetMapping(path="/coursePostByUser",produces="application/json;charset=UTF-8")
    public List<LessonPost> getPostByUser(HttpSession session) {
    	Users loggedInUser = (Users) session.getAttribute("logState");
    	return lpService.findLessPostByUsers(loggedInUser.getUsersId());
    }
    
    //取得課程公告
    @GetMapping(path="/coursePostByCourse/{lessonId}",produces="application/json;charset=UTF-8")
    public List<LessonPost> getPostByCourse(@PathVariable("lessonId") Integer lessonId){
    	return lpService.findLessonPost(lessonId);
    }
    
    //新增一筆公告
    @PostMapping(path="/coursePost/{lessonDetailId}",produces="application/json;charset=UTF-8")
    public LessonPost insertPost(@PathVariable("lessonDetailId")Integer lessonDetailId,@RequestBody LessonPost lessonPost,HttpSession session) {
    	Users user = new Users();
    	Users loggedInUser = (Users) session.getAttribute("logState");
    	user.setUsersId(loggedInUser.getUsersId());
    	LessonDetail detail = new LessonDetail();
    	detail.setLessonDetailId(lessonDetailId);
    	LessonPost post = new LessonPost();
    	post.setUsers(user);
    	post.setLessonDetail(detail);
    	post.setPostContent(lessonPost.getPostContent());
    	post.setTitle(lessonPost.getTitle());
    	
    	return lpService.createLessonPost(post);
    }
    
    //刪除一筆公告
    @DeleteMapping(path="/coursePost/{lessonPostId}")
    public void deletePost(@PathVariable Integer lessonPostId) {
    	lpService.deleteLessonPost(lessonPostId);
    }
    
    //-------------Note---------------------
    
    //取得使用者在這部影片的筆記
    @GetMapping(path="/videoNote/{videoId}",produces="application/json;charset=UTF-8")
    public List<VideoNote> getNoteByUserVideo(@PathVariable("videoId") Integer videoId,HttpSession session){
    	Users loggedInUser = (Users) session.getAttribute("logState");
    	return vnService.findByLessonAndUser(videoId, loggedInUser.getUsersId());
    }
    
    //新增筆記
    @PostMapping(path="/videoNote/{videoId}",produces="application/json;charset=UTF-8")
    public VideoNote inserVideoNote(@PathVariable("videoId")Integer videoId,@RequestBody VideoNote videoNote,HttpSession session) {
    	Users user = new Users();
    	Users loggedInUser = (Users) session.getAttribute("logState");
    	user.setUsersId(loggedInUser.getUsersId());
    	Video video = new Video();
    	video.setVideoId(videoId);
    	VideoNote savedvideoNote = new VideoNote();
    	savedvideoNote.setTimeLine(videoNote.getTimeLine());
    	savedvideoNote.setNoteContent(videoNote.getNoteContent());
    	savedvideoNote.setUsers(user);
    	savedvideoNote.setVideo(video);
    	return vnService.createVideoNote(savedvideoNote);
    }
    
    //刪除筆記
    @DeleteMapping(path="/videoNote/{videoNoteId}",produces="application/json;charset=UTF-8")
    public void deleteVideoNote(@PathVariable("videoNoteId") Integer videoNoteId) {
    	vnService.deleteVideoNote(videoNoteId);
    }
    
    //----------------willLearn--------------------
    
    //取得該課程willLearn
    @GetMapping(path="/willLearn/{lessonId}",produces="application/json;charset=UTF-8")
    public List<StudentWillLearn> findLessonWillLearn(@PathVariable("lessonId")Integer lessonId) {
    	return swlService.getLessonWillLearn(lessonId);
    }
    
    //刪除單筆willLearn
    @DeleteMapping(path="/willLearn/{willearnId}")
    public void deleteWillLearn(@PathVariable("willearnId") Integer willearnId) {
    	swlService.deleteWillLearn(willearnId);
    }
}
