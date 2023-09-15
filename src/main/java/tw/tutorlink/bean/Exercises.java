package tw.tutorlink.bean;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Exercises")
public class Exercises {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ExerId")
	private Integer exerId;

	@ManyToOne
	@JoinColumn(name = "LessonId", referencedColumnName = "lessonId")
	private Lessons lesson;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "UsersId", referencedColumnName = "usersId", nullable = false)
	private Users users;

	@Column(name = "ExerName", nullable = false, columnDefinition = "nvarchar(50)")
	private String exerName;

	@Column(name = "CreateDate")
	private Date createDate;

	// 關聯性欄位-----------------------------------------------------

	@JsonIgnoreProperties("exercises")
	@OneToMany(mappedBy = "exercises", cascade = CascadeType.ALL)
	private List<Topics> topics;
	
	@JsonIgnoreProperties("exercises")
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "exercises")
	private ExerciseConfig exerciseConfig;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.DETACH, mappedBy = "exercises" )
	private List<ExercisePermissions> exercisePermissions;
	
	@JsonIgnoreProperties("exercises")
	@OneToMany(mappedBy = "exercises", cascade = CascadeType.ALL)
	private List<Question> question;

	// -------------------------------------------------------------
	public Integer getExerId() {
		return exerId;
	}

	public void setExerId(Integer exerId) {
		this.exerId = exerId;
	}

	public Lessons getLesson() {
		return lesson;
	}

	public void setLesson(Lessons lesson) {
		this.lesson = lesson;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getExerName() {
		return exerName;
	}

	public ExerciseConfig getExerciseConfig() {
		return exerciseConfig;
	}

	public void setExerciseConfig(ExerciseConfig exerciseConfig) {
		this.exerciseConfig = exerciseConfig;
	}

	public void setExerName(String exerName) {
		this.exerName = exerName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public List<Topics> getTopics() {
		return topics;
	}

	public void setTopics(List<Topics> topics) {
		this.topics = topics;
	}



	public List<ExercisePermissions> getExercisePermissions() {
		return exercisePermissions;
	}

	public void setExercisePermissions(List<ExercisePermissions> exercisePermissions) {
		this.exercisePermissions = exercisePermissions;
	}

	public List<Question> getQuestion() {
		return question;
	}

	public void setQuestion(List<Question> question) {
		this.question = question;
	}

}
