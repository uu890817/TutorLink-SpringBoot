package tw.tutorlink.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="ExercisePermissions")
public class ExercisePermissions {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ExerPerId")
	private Integer exerPerId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"exercisePermissions", "topics", "exerciseConfig", "question", "lesson"})
	@JoinColumn(name = "ExerId", referencedColumnName = "exerId")
	private Exercises exercises;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "UsersId", referencedColumnName = "usersId", nullable = false)
	private Users users;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"exercisePermissions", "exerciseConfig"})
	@JoinColumn(name="ExerConfigId",referencedColumnName = "exerConfigId")
	private ExerciseConfig exerciseConfig;
	
	@Column(name="Score")
	private Integer score;
	
	@Column(name="OverwriteScore")
	private Integer overwriteScore;
	
	@JsonIgnoreProperties("ExercisePermissions")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "exercisePermissions")
	private List<StudentAnswers> studentAnswers;

	
	public Integer getExerPerId() {
		return exerPerId;
	}

	public void setExerPerId(Integer exerPerId) {
		this.exerPerId = exerPerId;
	}

	public Exercises getExercises() {
		return exercises;
	}

	public void setExercises(Exercises exercises) {
		this.exercises = exercises;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public ExerciseConfig getExerciseConfig() {
		return exerciseConfig;
	}

	public void setExerciseConfig(ExerciseConfig exerciseConfig) {
		this.exerciseConfig = exerciseConfig;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getOverwriteScore() {
		return overwriteScore;
	}

	public void setOverwriteScore(Integer overwriteScore) {
		this.overwriteScore = overwriteScore;
	}

	public List<StudentAnswers> getStudentAnswers() {
		return studentAnswers;
	}

	public void setStudentAnswers(List<StudentAnswers> studentAnswers) {
		this.studentAnswers = studentAnswers;
	}
	
	
	
	
	
	
	
	
}
