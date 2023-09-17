package tw.tutorlink.bean;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class CalenderDTO {

	private String studentName;
	
	private Integer lessonId;
	
	private Integer calenderId;
	
	private Date lessonTime;
	
	private String lessonName;
	
	private boolean lessonType;
	
	private String meetingUrl;
	
	
	public String getMeetingUrl() {
		return meetingUrl;
	}

	public void setMeetingUrl(String meetingUrl) {
		this.meetingUrl = meetingUrl;
	}

	public CalenderDTO(Lessons lesson, Users student,Calender calender) {
		this.lessonId = lesson.getLessonId();
		this.calenderId = calender.getCalenderId();
		this.lessonTime = calender.getLessonTime();
		this.lessonName = lesson.getLessonName();
		this.studentName = student.getUserDetail().getUserName();
		this.lessonType= lesson.getLessonType();
		this.meetingUrl= lesson.getLessondetail().getMeetingUrl();
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
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

	public CalenderDTO() {

	}

	public boolean isLessonType() {
		return lessonType;
	}

	public void setLessonType(boolean lessonType) {
		this.lessonType = lessonType;
	}


	
	
}
