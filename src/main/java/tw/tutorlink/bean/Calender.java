package tw.tutorlink.bean;

import java.util.Date;

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
	@JsonIgnoreProperties({ "userPassword", "userEmail", "userType", "googleSubId", "applyTeacher", "lesson", "order",
		"comment", "Cart", "report", "favorite", "exercises", "calender", "vacation", "question", "lessonPost",
		"videoNote", "courseQA", "cart","exercisePermissions","userDetail" })
	private Users users;

	@ManyToOne
	@JoinColumn(name = "LessonId", referencedColumnName = "lessonId", nullable = false)
	@JsonIgnoreProperties({ "subject", "lessonType", "price", "image", "lessondetail", "order",
		"shoppingCart", "report", "favorite", "exercises", "calender", "studentWillLearn", "courseQA" })
	private Lessons lesson;
	
	
	@Column(name = "LessonTime")
	private Date lessonTime;
	
	@OneToOne(mappedBy = "calender", cascade = CascadeType.ALL)
	private OrderItem orderitem;
	
	//付款狀態 0:未付款 1:已付款 2:已逾期
	@Column(name = "LessonType")
	private Integer lessonType;

	public Calender() {
	}

	
	public Calender(Users users, Lessons lesson, Date lessonTime, OrderItem orderitem, Integer lessonType) {
		this.users = users;
		this.lesson = lesson;
		this.lessonTime = lessonTime;
		this.orderitem = orderitem;
		this.lessonType = lessonType;
	}


	public Integer getLessonType() {
		return lessonType;
	}

	public void setLessonType(Integer lessonType) {
		this.lessonType = lessonType;
	}

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

	public Date getLessonTime() {
		return lessonTime;
	}

	public void setLessonTime(Date lessonTime) {
		this.lessonTime = lessonTime;
	}

	public OrderItem getOrderitem() {
		return orderitem;
	}

	public void setOrderitem(OrderItem orderitem) {
		this.orderitem = orderitem;
	}
	
	

}
