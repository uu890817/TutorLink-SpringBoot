package tw.tutorlink.bean;

import java.util.Date;

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
	
	@OneToOne
	@JoinColumn(name="LessonId",referencedColumnName = "lessonId", nullable = false)
	private Lessons lesson;
	
	@ManyToOne
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
}
