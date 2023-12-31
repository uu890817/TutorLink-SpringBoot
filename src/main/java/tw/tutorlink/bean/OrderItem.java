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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Orders")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OrderId")
	private Integer orderId;

	@ManyToOne
	@JsonIgnoreProperties({"order", "shoppingCart", "calender"})
	@JoinColumn(name="LessonId",referencedColumnName = "lessonId", nullable = false)
	private Lessons lesson;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "UsersId", referencedColumnName = "usersId",nullable = false)
	private Users users;
	
	@OneToOne
	@JsonIgnoreProperties({"orderitem", "calender"})
	@JoinColumn(name="CalenderId",referencedColumnName = "calenderId")
	private Calender calender;
	
	@Column(name="OrederStates")
	private Integer orderStates;
	
	@ManyToOne 
	@JsonIgnoreProperties({"order", "lesson"})
	@JoinColumn(name="CartId",referencedColumnName = "cartId", nullable = false)
	private CartItem cartItem;
	
	@Column(name = "CreateTime", nullable = false)
	private Date createTime;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
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

	public Calender getCalender() {
		return calender;
	}

	public void setCalender(Calender calender) {
		this.calender = calender;
	}

	public Integer getOrderStates() {
		return orderStates;
	}

	public void setOrderStates(Integer orderStates) {
		this.orderStates = orderStates;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public CartItem getCartItem() {
		return cartItem;
	}

	public void setCartItem(CartItem cartItem) {
		this.cartItem = cartItem;
	}
	
	
}
