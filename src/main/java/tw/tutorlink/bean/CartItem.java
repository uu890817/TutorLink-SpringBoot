package tw.tutorlink.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "CartItem")
public class CartItem {

	// 購物車項目流水號
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CartId")
	private Integer cartId;

	// 數量
	@Column(name = "Quantity")
	private Integer quantity;

	// 商品放入購物車時間
	@Column(name = "AddTime", columnDefinition = "date")
	private Date addTime;

	// 狀態
	// 0:購物車
	// 1:未結帳
	// 2:已結帳(訂單)
	@Column(name = "Status")
	private Integer status;

	// 哪個使用者的購物車
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "UsersId", referencedColumnName = "usersId", nullable = false)
	private Users users;

	// 課程編號
	@ManyToOne
	@JsonIgnoreProperties({ "shoppingCart" })
	@JoinColumn(name = "LessonId", referencedColumnName = "lessonId", nullable = false)
	private Lessons lesson;

	@Column(name = "SelectedTimes", length = 2000) // 適當指定字串長度
	private String selectedTimes;

	@JsonIgnoreProperties({ "cartItem" })
	@OneToMany(mappedBy = "cartItem", cascade = CascadeType.DETACH)
	private List<OrderItem> order;

	// 付款方式
	// 1:Line Pay
	@Column(name = "Payment")
	private Integer payment;

	// 關聯性欄位-----------------------------------------------------

	public CartItem() {
	}

	public List<Long> getSelectedTimes() {
		// 在需要使用時，將存儲的字串轉換回毫秒數的時間陣列
		// 你可以使用適當的方法將字串解析為毫秒數的時間陣列
		// 例如，使用 JSON 格式化
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			List<Long> timeList = objectMapper.readValue(selectedTimes, new TypeReference<List<Long>>() {
			});
			return timeList != null ? timeList : new ArrayList<>();
		} catch (IOException e) {
			// 處理異常
			return new ArrayList<>(); // 或者返回默認值
		}
	}

	public void setSelectedTimes(List<Long> selectedTimes) {
		// 在需要存儲時，將毫秒數的時間陣列轉換為字串
		// 你可以使用適當的方法將毫秒數的時間陣列序列化為字串
		// 例如，使用 JSON 格式化
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			this.selectedTimes = objectMapper.writeValueAsString(selectedTimes);
		} catch (JsonProcessingException e) {
			// 處理異常
			this.selectedTimes = "[]"; // 或者使用其他默認值
		}
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Lessons getLesson() {
		return lesson;
	}

	public void setLesson(Lessons lesson) {
		this.lesson = lesson;
	}

	public List<OrderItem> getOrder() {
		return order;
	}

	public void setOrder(List<OrderItem> order) {
		this.order = order;
	}

	public void setSelectedTimes(String selectedTimes) {
		this.selectedTimes = selectedTimes;
	}

	public Integer getPayment() {
		return payment;
	}

	public void setPayment(Integer payment) {
		this.payment = payment;
	}

}
