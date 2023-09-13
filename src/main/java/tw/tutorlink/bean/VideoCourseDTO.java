package tw.tutorlink.bean;


public class VideoCourseDTO {
	
	private String teacherName;
	
	private Integer lessonId;
	
	private String image;
	
	private String lessonName;
	
	public VideoCourseDTO(Lessons lesson, Users teacher) {
		this.lessonId = lesson.getLessonId();
		this.image = teacher.getUserDetail().getImage();
		this.teacherName = teacher.getUserDetail().getUserName();
		this.lessonName = lesson.getLessonName();
		
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public Integer getLessonId() {
		return lessonId;
	}

	public void setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	
	
	



    
    
}
