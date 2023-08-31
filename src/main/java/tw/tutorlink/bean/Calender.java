package tw.tutorlink.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@JsonIgnore
	private Users users;

	@ManyToOne
	@JoinColumn(name = "LessonId", referencedColumnName = "lessonId", nullable = false)
	@JsonIgnore
	private Lessons lesson;
	
	@Column(name = "lessonTimeStr")
	private String lessonTimeStr;
	
	
	@Column(name = "LessonTime")
	private String lessonTime;
	
	@OneToOne(mappedBy = "calender", cascade = CascadeType.ALL)
	private OrderItem orderitem;

	public Integer getCalenderId() {
		return calenderId;
	}

	public void setCalenderId(Integer calenderId) {
		this.calenderId = calenderId;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Lessons getLesson() {
		return lesson;
	}

	public void setLesson(Lessons lesson) {
		this.lesson = lesson;
	}

	public String getLessonTimeStr() {
		return lessonTimeStr;
	}

	public void setLessonTimeStr(String lessonTimeStr) {
		this.lessonTimeStr = lessonTimeStr;
	}

	public String getLessonTime() {
		return lessonTime;
	}

	public void setLessonTime(String lessonTime) {
		this.lessonTime = lessonTime;
	}

	public OrderItem getOrderitem() {
		return orderitem;
	}

	public void setOrderitem(OrderItem orderitem) {
		this.orderitem = orderitem;
	}
	
	

}
