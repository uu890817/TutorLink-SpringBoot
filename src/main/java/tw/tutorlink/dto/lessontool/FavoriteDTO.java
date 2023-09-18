package tw.tutorlink.dto.lessontool;

import org.springframework.stereotype.Component;

import tw.tutorlink.bean.Favorite;
import tw.tutorlink.bean.Lessons;
import tw.tutorlink.bean.Users;

@Component
public class FavoriteDTO {
	private Integer favoriteId;
	private Integer lessonId;
	private String lessonUrl;
	private String lessonName; 
	private String lessonInfo;
	private String teacherName;
	private boolean lessonType;
	private Integer subjectId;

	public FavoriteDTO(Lessons lesson, Users teacher,Favorite favorite) {
		this.favoriteId = favorite.getFavoriteId();
		this.lessonId = lesson.getLessonId();
		this.lessonUrl = lesson.getImage();
		this.lessonName = lesson.getLessonName();
		this.lessonInfo = lesson.getLessondetail().getImformation();
		this.teacherName = teacher.getUserDetail().getUserName();
		this.lessonType = lesson.getLessonType();
		this.subjectId = lesson.getSubject().getSubjectId();
	}
	public FavoriteDTO() {
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
	public Integer getFavoriteId() {
		return favoriteId;
	}
	public void setFavoriteId(Integer favoriteId) {
		this.favoriteId = favoriteId;
	}


	
	
}
