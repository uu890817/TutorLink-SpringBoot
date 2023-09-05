package tw.tutorlink.dto.exercises;

import tw.tutorlink.bean.Lessons;

public class TeacherGetAllLessonsName {

	private Integer lessonId;
	private String lessonName;
	private boolean lessonType;
	
	public TeacherGetAllLessonsName() {
		
	}
	public TeacherGetAllLessonsName(Lessons lessons) {
		super();
		this.lessonId = lessons.getLessonId();
		this.lessonName = lessons.getLessonName();
		this.lessonType = lessons.getLessonType();
	}
	
	public Integer getLessonId() {
		return lessonId;
	}
	public void setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
	}
	public String getLessonName() {
		return lessonName;
	}
	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}
	public boolean isLessonType() {
		return lessonType;
	}
	public void setLessonType(boolean lessonType) {
		this.lessonType = lessonType;
	}
	
	
}
