package tw.tutorlink.bean;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "LessonDetail")
public class LessonDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LessonDetailId")
	private Integer lessonDetailId;

	@JsonIncludeProperties("lessonId")
	@OneToOne
	@JoinColumn(referencedColumnName = "LessonId", nullable = false)
	private Lessons lesson;

	@Column(name = "MeetingUrl", columnDefinition = "varchar(100)")
	private String meetingUrl;

	@Column(name = "Information", columnDefinition = "nvarchar(max)")
	private String imformation;

	@Column(name = "CreateTime", columnDefinition = "Date")
	private Date createTime;

	@Column(name = "CourseUrl", columnDefinition = "varchar(100)")
	private String courseUrl;

	@Column(name = "CourserTotalTime")
	private Integer courseTotalTime;
	
	// 關聯性欄位-----------------------------------------------------

	@JsonIgnore
	@OneToMany(mappedBy = "lessonDetail",cascade = CascadeType.ALL)
	private List<Video> video;
	
	@JsonIgnore
	@OneToMany(mappedBy = "lessonDetail",cascade = CascadeType.ALL)
	private List<LessonPost> lessonPost;
	
	// Getter/Setter-----------------------------------------------
	
	public LessonDetail() {
		
	}
	public LessonDetail(String imformation,String meetingUrl,String courseUrl,Date createTime,Integer courseTotalTime) {
		this.imformation = imformation;
		this.meetingUrl = meetingUrl;
		this.createTime = createTime;
		this.courseUrl = courseUrl;
		this.courseTotalTime = courseTotalTime;
		
	}

	public Integer getLessonDetailId() {
		return lessonDetailId;
	}

	public void setLessonDetailId(Integer lessonDetailId) {
		this.lessonDetailId = lessonDetailId;
	}

	public Lessons getLesson() {
		return lesson;
	}

	public void setLesson(Lessons lesson) {
		this.lesson = lesson;
	}

	public String getMeetingUrl() {
		return meetingUrl;
	}

	public void setMeetingUrl(String meetingUrl) {
		this.meetingUrl = meetingUrl;
	}

	public String getImformation() {
		return imformation;
	}

	public void setImformation(String imformation) {
		this.imformation = imformation;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCourseUrl() {
		return courseUrl;
	}

	public void setCourseUrl(String courseUrl) {
		this.courseUrl = courseUrl;
	}

	public Integer getCourseTotalTime() {
		return courseTotalTime;
	}

	public void setCourseTotalTime(Integer courseTotalTime) {
		this.courseTotalTime = courseTotalTime;
	}

	public List<Video> getVideo() {
		return video;
	}

	public void setVideo(List<Video> video) {
		this.video = video;
	}

	public List<LessonPost> getLessonPost() {
		return lessonPost;
	}

	public void setLessonPost(List<LessonPost> lessonPost) {
		this.lessonPost = lessonPost;
	}
	
	
	
}
