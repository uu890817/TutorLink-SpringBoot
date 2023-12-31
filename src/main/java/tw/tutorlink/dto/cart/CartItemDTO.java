package tw.tutorlink.dto.cart;

import java.util.Date;
import java.util.List;

import tw.tutorlink.bean.CartItem;
import tw.tutorlink.bean.OrderItem;

public class CartItemDTO {
	
	public CartItemDTO() {
		super();

	}

	private Integer cartId;

	private String lessonName;
	
	private Integer lessonId;

	private boolean lessonType;

	private Integer price;

	private String image;
	
	private Integer quantity;
	
	private Date addTime;
	
	private Integer status;
	
	private List<Long> selectedTimes;
	
	private List<OrderItem> orderItem;
	
	private Integer payment;
	
	public CartItemDTO(CartItem item) {
		this.lessonId = item.getLesson().getLessonId();
		this.lessonName=item.getLesson().getLessonName();
		this.lessonType=item.getLesson().getLessonType();
		this.price=item.getLesson().getPrice();
		this.image=item.getLesson().getImage();
		this.quantity=item.getQuantity();
		this.addTime=item.getAddTime();
		this.status=item.getStatus();
		this.selectedTimes=item.getSelectedTimes();
		this.cartId=item.getCartId();
		this.orderItem=item.getOrder();
		this.payment=item.getPayment();
		this.lessonId=item.getLesson().getLessonId();
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


	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	public boolean isLessonType() {
		return lessonType;
	}

	public void setLessonType(boolean lessonType) {
		this.lessonType = lessonType;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	public List<OrderItem> getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(List<OrderItem> orderItem) {
		this.orderItem = orderItem;
	}

	public List<Long> getSelectedTimes() {
		return selectedTimes;
	}

	public Integer getLessonId() {
		return lessonId;
	}
	public void setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
	}
	public void setSelectedTimes(List<Long> selectedTimes) {
		this.selectedTimes = selectedTimes;
	}
	public Integer getPayment() {
		return payment;
	}
	public void setPayment(Integer payment) {
		this.payment = payment;
	}
}
