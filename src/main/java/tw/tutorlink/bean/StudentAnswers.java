package tw.tutorlink.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;


@Entity
@Table(name="StudentAnswers", uniqueConstraints = {@UniqueConstraint(columnNames = {"usersId", "topicsId"})})
public class StudentAnswers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="StudentAnsId")
	private Integer studentAnsId;
	
	@ManyToOne
	@JsonIgnoreProperties({"studentAnswers"})
	@JoinColumn(name="ExerPerId",referencedColumnName = "exerPerId")
	private ExercisePermissions exercisePermissions;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "UsersId", referencedColumnName = "usersId", nullable = false)
	private Users users;
	
	@ManyToOne
	@JsonIgnoreProperties({"studentAnswers"})
	@JoinColumn(name="TopicsId",referencedColumnName = "topicsId")
	private Topics topics;
	
	@Column(name="Answer")
	private String answer;
	
	

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Integer getStudentAnsId() {
		return studentAnsId;
	}

	public void setStudentAnsId(Integer studentAnsId) {
		this.studentAnsId = studentAnsId;
	}

	public ExercisePermissions getExercisePermissions() {
		return exercisePermissions;
	}

	public void setExercisePermissions(ExercisePermissions exercisePermissions) {
		this.exercisePermissions = exercisePermissions;
	}

	public Topics getTopics() {
		return topics;
	}

	public void setTopics(Topics topics) {
		this.topics = topics;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
	
	
	
	
}
