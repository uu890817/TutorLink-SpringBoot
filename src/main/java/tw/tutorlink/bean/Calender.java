package tw.tutorlink.bean;

import java.util.Date;

import jakarta.persistence.CascadeType;
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
@Table(name = "Calender")
public class Calender {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CalenderId")
	private Integer calenderId;

	@ManyToOne
	@JoinColumn(name = "UsersId", referencedColumnName = "usersId", nullable = false)
	private Users users;

	@ManyToOne
	@JoinColumn(name = "LessonId", referencedColumnName = "lessonId", nullable = false)
	private Lessons lesson;

	@Column(name = "StartTime", columnDefinition = "Date")
	private Date startTime;

	@Column(name = "EndTime", columnDefinition = "Date")
	private Date endTime;

	@OneToOne(mappedBy = "calender", cascade = CascadeType.ALL)
	private OrderItem orderitem;

}
