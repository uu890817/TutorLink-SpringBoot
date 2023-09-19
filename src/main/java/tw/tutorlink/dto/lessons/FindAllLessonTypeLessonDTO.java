package tw.tutorlink.dto.lessons;

import org.springframework.stereotype.Component;
import tw.tutorlink.bean.Lessons;
import tw.tutorlink.bean.Users;

@Component
public class FindAllLessonTypeLessonDTO {
	

	private Integer lessonId;
	private String lessonName; 
	private String teacherName;
	private Integer price;
	private boolean lessonType;
	private Integer subjectId;
	private String subjectName;
	
	public FindAllLessonTypeLessonDTO() {
		
	}
	public FindAllLessonTypeLessonDTO(Lessons lesson,Users teacher) {
		this.lessonId = lesson.getLessonId();
		this.lessonName = lesson.getLessonName();
		this.price = lesson.getPrice();
		this.lessonType = lesson.getLessonType();
		this.subjectId = lesson.getSubject().getSubjectId();
		this.subjectName = lesson.getSubject().getSubjectContent();
		this.teacherName = teacher.getUserDetail().getUserName();
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

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	
	
}
