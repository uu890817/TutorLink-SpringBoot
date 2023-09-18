package tw.tutorlink.dto.lessontool;

import java.util.Date;

import org.springframework.stereotype.Component;

import tw.tutorlink.bean.Calender;
import tw.tutorlink.bean.Lessons;
import tw.tutorlink.bean.Users;

@Component
public class LessonsDTO {

	private String teacherName;
	
	private Integer lessonId;
	
	private Integer calenderId;
	
	private Date lessonTime;
	
	private String lessonName;

	public LessonsDTO(Lessons lesson, Users teacher,Calender calender) {
		this.lessonId = lesson.getLessonId();
		this.calenderId = calender.getCalenderId();
		this.lessonTime = calender.getLessonTime();
		this.lessonName = lesson.getLessonName();
		this.teacherName = teacher.getUserDetail().getUserName();
		
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

	public Integer getCalenderId() {
		return calenderId;
	}

	public void setCalenderId(Integer calenderId) {
		this.calenderId = calenderId;
	}

	public Date getLessonTime() {
		return lessonTime;
	}

	public void setLessonTime(Date lessonTime) {
		this.lessonTime = lessonTime;
	}

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	public LessonsDTO() {

	}


	
	
}
