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
@Table(name = "Lesson")
public class Lesson {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LessonId")
	private Integer lessonId;

	@ManyToOne
	@JoinColumn(name = "UsersId", referencedColumnName = "usersId")
	private Users users;

	@Column(name = "LessonName")
	private String lessonName;

	@Column(name = "Image")
	private String image;

	@Column(name = "LeesonType")
	private Integer lessonType;

	@OneToOne(mappedBy = "lesson", cascade = CascadeType.ALL)
	private LessonDetail lessondetail;

	@OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
	private List<Orders> order;

	@OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
	private List<Comment> comment;

	@OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
	private List<ShoppingCart> shoppingCart;

	@OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
	private List<Report> report;

	@OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
	private List<Favorite> favorite;
	
	@OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL)
	private List<Exercises> exercises;


}
