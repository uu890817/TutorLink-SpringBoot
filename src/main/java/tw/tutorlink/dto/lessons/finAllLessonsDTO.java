package tw.tutorlink.dto.lessons;

import org.springframework.stereotype.Component;

import tw.tutorlink.bean.Lessons;
import tw.tutorlink.bean.Users;

@Component
public class finAllLessonsDTO {

	private Integer lessonId;
	private String lessonUrl;
	private String lessonName; 
	private String lessonInfo;
	private String teacherName;
	private Integer price;
	private boolean lessonType;
	private Integer subjectId;

	public finAllLessonsDTO(Lessons lesson, Users teacher) {
		this.lessonId = lesson.getLessonId();
		this.lessonUrl = lesson.getImage();
		this.lessonName = lesson.getLessonName();
		this.lessonInfo = lesson.getLessondetail().getImformation();
		this.teacherName = teacher.getUserDetail().getUserName();
		this.price = lesson.getPrice();
		this.lessonType = lesson.getLessonType();
		this.subjectId = lesson.getSubject().getSubjectId();
	}
	public finAllLessonsDTO() {
	}
	
	public Integer getLessonId() {
		return lessonId;
	}
	public void setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
	}
	public String getLessonUrl() {
		return lessonUrl;
	}
	public void setLessonUrl(String lessonUrl) {
		this.lessonUrl = lessonUrl;
	}
	public String getLessonName() {
		return lessonName;
	}
	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}
	public String getLessonInfo() {
		return lessonInfo;
	}
	public void setLessonInfo(String lessonInfo) {
		this.lessonInfo = lessonInfo;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public boolean isLessonType() {
		return lessonType;
	}
	public void setLessonType(boolean lessonType) {
		this.lessonType = lessonType;
	}
	public Integer getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}


	
	
}
