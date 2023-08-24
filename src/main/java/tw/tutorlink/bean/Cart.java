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
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CartId")
	private Integer cartId;

	@ManyToOne
	@JoinColumn(name="LessonId",referencedColumnName = "lessonId", nullable = false)
	private Lessons lesson;

	@ManyToOne
	@JoinColumn(name = "UsersId", referencedColumnName = "usersId",nullable = false)
	private Users users;

	@Column(name="Quantity")
	private Integer quantity;
	
	@Column(name="AddTime",columnDefinition = "date")
	private Date addTime;
	
	@Column(name="IsUpdateTime",columnDefinition = "date")
	private Date lastUpdateTime;
	
	@Column(name="Status")
	private Integer status;
}
