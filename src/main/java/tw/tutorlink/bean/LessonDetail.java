package tw.tutorlink.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "LessonDetail")
public class LessonDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LessonDtId")
	private Integer lessonDetail;

	@JsonIgnore
	@OneToOne
	@JoinColumn(referencedColumnName = "LessonId", nullable = false)
	private Lesson lesson;

	@Column(name = "Video")
	private String video;

	@Column(name = "CreateTime", nullable = false)
	private Date createTime;

	@Column(name = "UpdateTime")
	private Date updateTime;

	@Column(name = "Infomation")
	private String information;
}
