package tw.tutorlink.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="CourseQA")
public class CourseQA {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CourseQAId")
	private Integer courseQAId;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="LessonId",referencedColumnName = "lessonId", nullable = false)
	private Lessons lesson;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "UsersId", referencedColumnName = "usersId",nullable = false)
	private Users users;
	
	@Column(name="Title",columnDefinition = "nvarchar(50)")
	private String title;
	
	@Column(name="Question",columnDefinition = "nvarchar(max)")
	private String question;
	
	@Column(name="Answer",columnDefinition = "nvarchar(max)")
	private String answer;
	
	@Column(name="Time",columnDefinition = "date")
	private Date time;

	

	public CourseQA() {
	}


	public CourseQA(Integer courseQAId, Lessons lesson, Users users, String title, String question, String answer,
			Date time) {
		super();
		this.courseQAId = courseQAId;
		this.lesson = lesson;
		this.users = users;
		this.title = title;
		this.question = question;
		this.answer = answer;
		this.time = time;
	}

	
	public Integer getCourseQAId() {
		return courseQAId;
	}

	public void setCourseQAId(Integer courseQAId) {
		this.courseQAId = courseQAId;
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	
}
