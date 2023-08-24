package tw.tutorlink.bean;

import java.util.Date;
import java.util.List;

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
	@JoinColumn(name = "LessonId", referencedColumnName = "lessonId", nullable = false)
	private Lessons lesson;

	@ManyToOne
	@JoinColumn(name = "UsersId", referencedColumnName = "usersId", nullable = false)
	private Users users;

	@Column(name = "ExerName", nullable = false,columnDefinition = "nvarchar(50)")
	private String exerName;

	@Column(name = "CreateDate")
	private Date createDate;
	
	// 關聯性欄位-----------------------------------------------------

	@OneToMany(mappedBy = "exercises", cascade = CascadeType.ALL)
	private List<Topics> topics;
	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "exercises")
	private ExercisePermissions exercisePermissions;
	
	@OneToMany(mappedBy = "exercises", cascade = CascadeType.ALL)
	private List<Question> question;
}


