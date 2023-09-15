package tw.tutorlink.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import tw.tutorlink.bean.CartItem;
import tw.tutorlink.bean.Lessons;
import tw.tutorlink.bean.OrderItem;
import tw.tutorlink.bean.Users;
import tw.tutorlink.dto.cart.AddIntoCartDTO;
import tw.tutorlink.dto.cart.CartItemDTO;
import tw.tutorlink.dto.cart.CartToOrder;
import tw.tutorlink.service.CalenderService;
import tw.tutorlink.service.CartService;
import tw.tutorlink.service.LessonsService;
import tw.tutorlink.service.OrderItemService;

@RestController
@RequestMapping("/shoppingcart")
public class ShoppingCartController {

	@Autowired
	private CartService cService;

	@Autowired
	private OrderItemService oService;

	@Autowired
	private LessonsService lessonService;
	
	@Autowired
	private CalenderService calenderService;

	// 查詢使用者購物車商品
	@GetMapping("/step1")
	@ResponseBody
	public List<CartItemDTO> getMyShoppingCart(HttpSession session) {
		Users loggedInUser = (Users) session.getAttribute("logState");
		return cService.getUserShoppingCart(loggedInUser.getUsersId());
//		return cService.getUserShoppingCart(6);
	}

	// 刪除使用者購物車商品
	@DeleteMapping(path = "/deleteCartItem/{cId}")
	public String deleteCartItem(@PathVariable Integer cId) {
		System.out.println(cId);
		return cService.deleteCartItem(cId);
	}

	// 更新使用者購物車商品
	@PutMapping(path = "/updateItemCount/{cId}", produces = "application/json;charset=UTF-8")
	public String updateCartItem(HttpSession session, @RequestBody CartItemDTO cDTO) {
		System.out.println(cDTO.getQuantity());
		cService.updateCartItem(cDTO);
		return null;
	}

	// 加入購物車
	@PostMapping(path = "/add")
	public CartItemDTO addToCart(HttpSession session, @RequestBody AddIntoCartDTO addIntoCartDTO) {
		Users loggedInUser = (Users) session.getAttribute("logState");
		CartItem cartItem = new CartItem();
		Lessons addLessonIntoCartById = lessonService.AddLessonIntoCartById(addIntoCartDTO.getLessonsId());
		cartItem.setAddTime(addIntoCartDTO.getAddTime());
		cartItem.setUsers(loggedInUser);
		cartItem.setStatus(0);
		cartItem.setQuantity(1);
		cartItem.setSelectedTimes("[]");
		cartItem.setPayment(1);
		cartItem.setLesson(addLessonIntoCartById);
		cService.insertNewCartItem(cartItem);
		CartItemDTO shoppingCart = cService.getShoppingCart(cartItem.getCartId());
		return shoppingCart;
	}

	// 結帳
	@PostMapping(path = "/pay", produces = "application/json;charset=UTF-8")
	public String pay(HttpSession session, @RequestBody CartToOrder cartToOrder) {
		Users loggedInUser = (Users) session.getAttribute("logState");
		System.out.println(cartToOrder);
		cService.deleteAllCartItem(loggedInUser.getUsersId());
		OrderItem orderItem = new OrderItem();
		orderItem.setCartItem(cService.findByCartId(cartToOrder.getCartId()));
		orderItem.setCreateTime(cartToOrder.getCreateTime());
		orderItem.setLesson(lessonService.AddLessonIntoCartById(cartToOrder.getLessonId()));
		orderItem.setUsers(loggedInUser);
		orderItem.setOrderStates(0);
		if(orderItem.getLesson().getLessonType()) {
			orderItem.setCalender(calenderService.insertByTimes(cartToOrder.getCalender(), loggedInUser, lessonService.AddLessonIntoCartById(cartToOrder.getLessonId())));
		}
		oService.insertOrderItem(orderItem);
		return "結帳成功";
	}
}
