package tw.tutorlink.bean;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "Comment")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ComId")
	private Integer comId;

	@ManyToOne
	@JoinColumn(name = "LessonId", referencedColumnName = "lessonId", nullable = false)
	@JsonIgnoreProperties({ "subject", "lessonType", "price", "image", "lessonId", "lessondetail", "order",
			"shoppingCart", "report", "favorite", "exercises", "calender", "studentWillLearn", "courseQA" })
	private Lessons lesson;

	@ManyToOne
	@JoinColumn(name = "UsersId", referencedColumnName = "usersId", nullable = false)
	@JsonIgnoreProperties({ "userPassword", "userEmail", "userType", "googleSubId", "applyTeacher", "lesson", "order",
			"comment", "Cart", "report", "favorite", "exercises", "calender", "vacation", "question", "lessonPost",
			"videoNote", "courseQA", "cart" })
	private Users users;

	@Column(name = "RateContent", columnDefinition = "nvarchar(500)")
	private String rateContent;

	@Column(name = "Rate", nullable = false)
	private Integer rate;

	@Column(name = "CreateTime", nullable = false)
	private Date createTime;

	public Comment() {
		super();
	}

	public Comment(Lessons lesson, Users users, String rateContent, Integer rate, Date createTime) {
		super();
		this.lesson = lesson;
		this.users = users;
		this.rateContent = rateContent;
		this.rate = rate;
		this.createTime = createTime;
	}

	public Integer getComId() {
		return comId;
	}

	public void setComId(Integer comId) {
		this.comId = comId;
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

	public String getRateContent() {
		return rateContent;
	}

	public void setRateContent(String rateContent) {
		this.rateContent = rateContent;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
