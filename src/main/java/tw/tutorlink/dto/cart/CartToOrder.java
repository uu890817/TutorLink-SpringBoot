package tw.tutorlink.dto.cart;

import java.util.Date;

public class CartToOrder {

	private Integer lessonId;
	
	private Date createTime;
	
	private Integer cartId;

	private Date calender;
	
	private Integer orderStates;
	
	public CartToOrder() {
		// TODO Auto-generated constructor stub
	}

	public Integer getLessonId() {
		return lessonId;
	}

	public void setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
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
	
	public Integer getOrderStates() {
		return orderStates;
	}

	public void setOrderStates(Integer orderStates) {
		this.orderStates = orderStates;
	}

	public Date getCalender() {
		return calender;
	}

	public void setCalender(Date calender) {
		this.calender = calender;
	}

}
