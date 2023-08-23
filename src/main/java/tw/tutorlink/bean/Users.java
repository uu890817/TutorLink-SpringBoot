package tw.tutorlink.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Users")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "UsersId")
	private Integer usersId;

	@Column(name = "UserAccount", nullable = false, columnDefinition = "varchar(50)")
	private String userAccount;

	@Column(name = "UserPassword", nullable = false, columnDefinition = "varchar(50)")
	private String userPassword;

	@Column(name = "UserEamil", nullable = false, columnDefinition = "varchar(50)")
	private String userEmail;

	@Column(name = "UserType", nullable = false)
	private Integer userType;

	@Column(name = "GoolgeToken", columnDefinition = "varchar(100)")
	private String googleToken;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "users")
	private UserDetail userDetail;

	@JsonIgnore
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	private List<Lesson> lesson;

	@JsonIgnore
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	private List<Orders> order;

	@JsonIgnore
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	private List<Comment> comment;

	@JsonIgnore
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	private List<ShoppingCart> shoppingCart;

	@JsonIgnore
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	private List<Report> report;

	@JsonIgnore
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	private List<Favorite> favorite;

	@JsonIgnore
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	private List<Exercises> exercises;

	@JsonIgnore
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	private List<Lectures> lectures;

	@JsonIgnore
	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	private List<ExercisePermissions> exercisePermissions;

}
