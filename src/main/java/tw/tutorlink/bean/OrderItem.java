package tw.tutorlink.bean;

import java.util.Date;

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
	@JoinColumn(name="LessonId",referencedColumnName = "lessonId", nullable = false)
	private Lessons lesson;

	@ManyToOne
	@JoinColumn(name = "UsersId", referencedColumnName = "usersId",nullable = false)
	private Users users;
	
	@OneToOne
	@JoinColumn(name="CalenderId",referencedColumnName = "calenderId")
	private Calender calender;

	@Column(name = "Type",columnDefinition = "bit")
	private boolean type;
	
	@Column(name="OrederStates")
	private Integer orderStates;
	
	@Column(name="Coupon")
	private String coupon;
	
	@Column(name = "CreateTime", nullable = false)
	private Date createTime;
	
	
}
