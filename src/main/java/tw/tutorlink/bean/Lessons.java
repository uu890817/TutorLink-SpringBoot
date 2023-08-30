package tw.tutorlink.bean;

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
@Table(name = "Lessons")
public class Lessons {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LessonId")
	private Integer lessonId;
	
	@ManyToOne
	@JoinColumn(name = "UsersId", referencedColumnName = "usersId")
	private Users users;
	
	@ManyToOne
	@JoinColumn(name="SubjectId",referencedColumnName = "subjectId")
	private Subject subject;

	@Column(name = "LessonName", columnDefinition = "nvarchar(50)")
	private String lessonName;

	@Column(name = "LeesonType", columnDefinition = "bit")
	private boolean lessonType;

	@Column(name = "Image")
	private String image;

	// 關聯性欄位-----------------------------------------------------

	@OneToOne(mappedBy = "lesson", cascade = CascadeType.ALL)
	private LessonDetail lessondetail;

	@OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
	private List<OrderItem> order;

	@OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
	private List<Score> comment;

	@OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
	private List<Cart> shoppingCart;

	@OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
	private List<Report> report;

	@OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
	private List<Favorite> favorite;

	@OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
	private List<Exercises> exercises;
	
	@OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
	private List<Calender> calender;
	
	@OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
	private List<StudentWillLearn> studentWillLearn;
	
	@OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
	private List<CourseQA> courseQA;

	// Getter/Setter-----------------------------------------------
	
	public Integer getLessonId() {
		return lessonId;
	}
	
	public void setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
	}
	
	public Users getUsers() {
		return users;
	}
	
	public void setUsers(Users users) {
		this.users = users;
	}
	
	public Subject getSubject() {
		return subject;
	}
	
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
	public String getLessonName() {
		return lessonName;
	}
	
	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}
	
	public boolean getLessonType() {
		return lessonType;
	}
	
	public void setLessonType(boolean lessonType) {
		this.lessonType = lessonType;
	}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public LessonDetail getLessondetail() {
		return lessondetail;
	}
	
	public void setLessondetail(LessonDetail lessondetail) {
		this.lessondetail = lessondetail;
	}
	
	public List<OrderItem> getOrder() {
		return order;
	}
	
	public void setOrder(List<OrderItem> order) {
		this.order = order;
	}
	
	public List<Score> getComment() {
		return comment;
	}
	
	public void setComment(List<Score> comment) {
		this.comment = comment;
	}
	
	public List<Cart> getShoppingCart() {
		return shoppingCart;
	}
	
	public void setShoppingCart(List<Cart> shoppingCart) {
		this.shoppingCart = shoppingCart;
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
	
	public List<Calender> getCalender() {
		return calender;
	}
	
	public void setCalender(List<Calender> calender) {
		this.calender = calender;
	}
	
	public List<StudentWillLearn> getStudentWillLearn() {
		return studentWillLearn;
	}
	
	public void setStudentWillLearn(List<StudentWillLearn> studentWillLearn) {
		this.studentWillLearn = studentWillLearn;
	}

	public List<CourseQA> getCourseQA() {
		return courseQA;
	}

	public void setCourseQA(List<CourseQA> courseQA) {
		this.courseQA = courseQA;
	}
	
	
	
	
	
}
