package tw.tutorlink.bean;

import org.springframework.web.multipart.MultipartFile;

public class VideoUploadDTO {
	private LessonDetail lessonDetail;
	private Integer sort;
    private String chapterName;
    private MultipartFile videoFile;
    
	public VideoUploadDTO() {
	}
	
	
	
	public VideoUploadDTO(LessonDetail lessonDetail, Integer sort, String chapterName, MultipartFile videoFile) {
		this.lessonDetail = lessonDetail;
		this.sort = sort;
		this.chapterName = chapterName;
		this.videoFile = videoFile;
	}



	public LessonDetail getLessonDetail() {
		return lessonDetail;
	}
	public void setLessonDetail(LessonDetail lessonDetail) {
		this.lessonDetail = lessonDetail;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getChapterName() {
		return chapterName;
	}
	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}
	public MultipartFile getVideoFile() {
		return videoFile;
	}
	public void setVideoFile(MultipartFile videoFile) {
		this.videoFile = videoFile;
	}
    
    
    
}
