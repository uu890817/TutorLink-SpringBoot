package tw.tutorlink.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Favorite")
public class Favorite {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FavoriteId")
	private Integer favoriteId;

	@ManyToOne
	@JoinColumn(name = "LessonId", referencedColumnName = "lessonId", nullable = false)
	@JsonIgnoreProperties({ "subject", "lessonType", "price", "image", "lessondetail", "order",
		"shoppingCart", "report", "favorite", "exercises", "calender", "studentWillLearn", "courseQA" })
	private Lessons lesson;

	@ManyToOne
	@JoinColumn(name = "UsersId", referencedColumnName = "usersId", nullable = false)
	@JsonIgnoreProperties({ "userPassword", "userEmail", "userType", "googleSubId", "applyTeacher", "lesson", "order",
		"comment", "Cart", "report", "favorite", "exercises", "calender", "vacation", "question", "lessonPost",
		"videoNote", "courseQA", "cart","exercisePermissions","userDetail" })
	private Users users;

	@Column(name = "Time",columnDefinition = "Date")
	private Date Time;

	public Favorite() {
		super();
	}

	public Favorite(Integer favoriteId, Lessons lesson, Users users, Date time) {
		super();
		this.favoriteId = favoriteId;
		this.lesson = lesson;
		this.users = users;
		Time = time;
	}

	public Integer getFavoriteId() {
		return favoriteId;
	}

	public void setFavoriteId(Integer favoriteId) {
		this.favoriteId = favoriteId;
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

	public Date getTime() {
		return Time;
	}

	public void setTime(Date time) {
		Time = time;
	}
	
	

}
