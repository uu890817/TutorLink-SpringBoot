package tw.tutorlink.dto.lessontool;

import java.util.Date;

import org.springframework.stereotype.Component;

import tw.tutorlink.bean.Comment;
import tw.tutorlink.bean.Lessons;
import tw.tutorlink.bean.Users;

@Component
public class CommentDTO {
	private Integer comId;
	private Date createTime;
	private Integer lessonId;
	private String lessonUrl;
	private String lessonName; 
	private String rateContent;
	private String teacherName;
	private String rateTags;
	private String studentName;
	private Integer rate;

	public CommentDTO(Lessons lesson, Users teacher,Comment comment,Users student) {
		this.comId = comment.getComId();
		this.createTime = comment.getCreateTime();
		this.lessonId = lesson.getLessonId();
		this.lessonUrl = lesson.getImage();
		this.lessonName = lesson.getLessonName();
		this.rateContent = comment.getRateContent();
		this.teacherName = teacher.getUserDetail().getUserName();
		this.rateTags = comment.getRateTags();
		this.studentName=student.getUserDetail().getUserName();
		this.rate = comment.getRate();
	}
	public CommentDTO() {
	}
	public Integer getComId() {
		return comId;
	}
	public void setComId(Integer comId) {
		this.comId = comId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	public String getRateContent() {
		return rateContent;
	}
	public void setRateContent(String rateContent) {
		this.rateContent = rateContent;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getRateTags() {
		return rateTags;
	}
	public void setRateTags(String rateTags) {
		this.rateTags = rateTags;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public Integer getRate() {
		return rate;
	}
	public void setRate(Integer rate) {
		this.rate = rate;
	}
	
}
