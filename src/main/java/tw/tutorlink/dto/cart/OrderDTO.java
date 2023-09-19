package tw.tutorlink.dto.cart;

import java.util.Date;

import tw.tutorlink.bean.OrderItem;

public class OrderDTO {

	private Integer orderId;

	private Integer uerId;
	
	private String uerName;

	private boolean lessonType;

	private String lessonName;

	private Integer lessonId;

	private String image;

	private Integer quantity;

	private Integer orderStates;

	private Integer payment;

	private Date createTime;

	private Integer cartId;

	private Integer price;

	private Date lessonTime;

	public OrderDTO(OrderItem item) {
		super();
		this.orderId = item.getOrderId();
		this.uerId = item.getUsers().getUsersId();
		this.lessonType = item.getLesson().getLessonType();
		this.lessonName = item.getLesson().getLessonName();
		this.lessonId = item.getLesson().getLessonId();
		this.image = item.getLesson().getImage();
		this.quantity = item.getCartItem().getQuantity();
		this.orderStates = item.getOrderStates();
		this.payment = item.getCartItem().getPayment();
		this.createTime = item.getCreateTime();
		this.uerName = item.getUsers().getUserDetailUserName();
		this.cartId = item.getCartItem().getCartId();
		this.price = item.getLesson().getPrice();
		if (item.getCalender() != null) {
			this.lessonTime = item.getCalender().getLessonTime();
		}
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public OrderDTO() {
		super();
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getUerId() {
		return uerId;
	}

	public void setUerId(Integer uerId) {
		this.uerId = uerId;
	}

	public boolean isLessonType() {
		return lessonType;
	}

	public void setLessonType(boolean lessonType) {
		this.lessonType = lessonType;
	}

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	public Integer getLessonId() {
		return lessonId;
	}

	public void setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getOrderStates() {
		return orderStates;
	}

	public void setOrderStates(Integer orderStates) {
		this.orderStates = orderStates;
	}

	public Integer getPayment() {
		return payment;
	}

	public void setPayment(Integer payment) {
		this.payment = payment;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCartId() {
		return cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public String getUerName() {
		return uerName;
	}

	public void setUerName(String uerName) {
		this.uerName = uerName;
	}

	public Date getLessonTime() {
		return lessonTime;
	}

	public void setLessonTime(Date lessonTime) {
		this.lessonTime = lessonTime;
	}

}
