package tw.tutorlink.bean;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

	@JsonIgnore
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
}
