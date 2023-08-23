package tw.tutorlink.bean;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ShoppingCart")
public class ShoppingCart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ShopId")
	private Integer shopId;

	@ManyToOne
	@JoinColumn(name="LessonId",referencedColumnName = "lessonId", nullable = false)
	private Lesson lesson;

	@ManyToOne
	@JoinColumn(name = "UsersId", referencedColumnName = "usersId",nullable = false)
	private Users users;

	@Column(name = "Date")
	private Date date;
}
