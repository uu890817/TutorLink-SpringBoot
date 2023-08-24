package tw.tutorlink.bean;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Comment")
public class Score {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ComId")
	private Integer comId;

	@ManyToOne
	@JoinColumn(name="LessonId",referencedColumnName = "lessonId", nullable = false)
	private Lessons lesson;

	@ManyToOne
	@JoinColumn(name = "UsersId", referencedColumnName = "usersId",nullable = false)
	private Users users;

	@Column(name = "ScoreContent", columnDefinition = "nvarchar(500)")
	private String scoreContent;

	@Column(name = "Score", nullable = false)
	private Integer Score;

	@Column(name = "CreateTime", nullable = false)
	private Date createTime;
}