package tw.tutorlink.bean;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Transient;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Users")
public class Users{

	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UsersId")
	private Integer usersId;

	@Column(name = "UserPassword", columnDefinition = "nvarchar(max)")
	private String userPassword;

	@Column(name = "UserEmail", columnDefinition = "varchar(50)")
	private String userEmail;

	@Column(name = "UserType")
	private Integer userType;

	@Column(name = "GoogleSubId", columnDefinition = "varchar(100)")
	private String googleSubId;
	
	@Column(name="RamdonVerify")
	private Integer ramdonVerify;
	
	@Column(name="ExpiredTime")
	private Date expiredTime;

	// 關聯性欄位-----------------------------------------------------

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "users")
	private UserDetail userDetail;

	@OneToOne(mappedBy = "users", cascade = CascadeType.ALL)
	private ApplyTeacher applyTeacher;

//	@JsonIgnore
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	private List<Lessons> lesson;

//	@JsonIgnore
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<OrderItem> order;

//	@JsonIgnore
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	private List<Comment> comment;

//	@JsonIgnore
	@OneToMany(mappedBy = "users", cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
	private List<CartItem> cartItem;

//	@JsonIgnore
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	private List<Report> report;

//	@JsonIgnore
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	private List<Favorite> favorite;

//	@JsonBackReference
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	private List<Exercises> exercises;

//	@JsonIgnore
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	private List<ExercisePermissions> exercisePermissions;

//	@JsonIgnore
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private List<Calender> calender;

//	@JsonIgnore
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	private List<Vacation> vacation;

//	@JsonIgnore
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	private List<Question> question;

//	@JsonIgnore
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	private List<LessonPost> lessonPost;

//	@JsonIgnore
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	private List<VideoNote> videoNote;

//	@JsonIgnore
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	private List<CourseQA> courseQA;

	public Integer getUsersId() {
		return usersId;
	}

	public void setUsersId(Integer usersId) {
		this.usersId = usersId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getGoogleSubId() {
		return googleSubId;
	}

	public void setGoogleSubId(String googleSubId) {
		this.googleSubId = googleSubId;
	}

	public UserDetail getUserDetail() {
		return userDetail;
	}

	public void setUserDetail(UserDetail userDetail) {
		this.userDetail = userDetail;
	}

	public ApplyTeacher getApplyTeacher() {
		return applyTeacher;
	}

	public void setApplyTeacher(ApplyTeacher applyTeacher) {
		this.applyTeacher = applyTeacher;
	}

	public List<Lessons> getLesson() {
		return lesson;
	}

	public void setLesson(List<Lessons> lesson) {
		this.lesson = lesson;
	}

	public List<OrderItem> getOrder() {
		return order;
	}

	public void setOrder(List<OrderItem> order) {
		this.order = order;
	}

	public List<Comment> getComment() {
		return comment;
	}

	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}

	public List<CartItem> getCart() {
		return cartItem;
	}

	public void setCart(List<CartItem> cart) {
		this.cartItem = cart;
	}

	public List<Report> getReport() {
		return report;
	}

	public void setReport(List<Report> report) {
		this.report = report;
	}

	public List<Favorite> getFavorite() {
		return favorite;
	}

	public void setFavorite(List<Favorite> favorite) {
		this.favorite = favorite;
	}

	public List<Exercises> getExercises() {
		return exercises;
	}

	public void setExercises(List<Exercises> exercises) {
		this.exercises = exercises;
	}

	public List<ExercisePermissions> getExercisePermissions() {
		return exercisePermissions;
	}

	public void setExercisePermissions(List<ExercisePermissions> exercisePermissions) {
		this.exercisePermissions = exercisePermissions;
	}

	public List<Calender> getCalender() {
		return calender;
	}

	public void setCalender(List<Calender> calender) {
		this.calender = calender;
	}

	public List<Vacation> getVacation() {
		return vacation;
	}

	public void setVacation(List<Vacation> vacation) {
		this.vacation = vacation;
	}

	public List<Question> getQuestion() {
		return question;
	}

	public void setQuestion(List<Question> question) {
		this.question = question;
	}

	public List<LessonPost> getLessonPost() {
		return lessonPost;
	}

	public void setLessonPost(List<LessonPost> lessonPost) {
		this.lessonPost = lessonPost;
	}

	public List<VideoNote> getVideoNote() {
		return videoNote;
	}

	public void setVideoNote(List<VideoNote> videoNote) {
		this.videoNote = videoNote;
	}

	public List<CourseQA> getCourseQA() {
		return courseQA;
	}

	public void setCourseQA(List<CourseQA> courseQA) {
		this.courseQA = courseQA;
	}
	

	public Integer getRamdonVerify() {
		return ramdonVerify;
	}

	public void setRamdonVerify(Integer ramdonVerify) {
		this.ramdonVerify = ramdonVerify;
	}

	public Date getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}

	@Transient
	public String getUserDetailUserName() {
	    return this.userDetail != null ? this.userDetail.getUserName() : null;
	}
}
