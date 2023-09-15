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
import tw.tutorlink.bean.OrderItem;
import tw.tutorlink.bean.Users;
import tw.tutorlink.dto.cart.CartItemDTO;
import tw.tutorlink.service.CartService;
import tw.tutorlink.service.OrderItemService;

@RestController
@RequestMapping("/shoppingcart")
public class ShoppingCartController {
	
	@Autowired
	private CartService cService;
	
	@Autowired
	private OrderItemService oService;
	
	
//	@GetMapping("/CartItem")
//	public List<CartItemDTO> testApi() {
//		return cService.getUserShoppingCart(6);
//	}
	
	//查詢使用者購物車商品
	@GetMapping("/step1")
	@ResponseBody
	public List<CartItemDTO> getMyShoppingCart(HttpSession session) {
		Users loggedInUser = (Users) session.getAttribute("logState");
//		return cService.getUserShoppingCart(loggedInUser.getUsersId());
		return cService.getUserShoppingCart(6);
	}
	
	//刪除使用者購物車商品
	@DeleteMapping(path = "/deleteCartItem/{cId}")
	public String deleteCartItem(@PathVariable Integer cId) {
		System.out.println(cId);
		return cService.deleteCartItem(cId);
	}
	
	//更新使用者購物車商品
	@PutMapping(path = "/updateItemCount/{cId}", produces = "application/json;charset=UTF-8")
	public String updateCartItem(HttpSession session,@RequestBody CartItemDTO cDTO) {
		System.out.println(cDTO.getQuantity());
		cService.updateCartItem(cDTO);
		return null;
	}
	
	// 加入購物車
	@PostMapping("/add")
	public String addToCart(HttpSession session,@RequestBody CartItem cartItem) {
		Users loggedInUser = (Users) session.getAttribute("logState");
		cService.insertNewCartItem(cartItem);
		return "加入購物車成功";
	}
	
	//結帳
	@PostMapping("/pay")
	public String pay(HttpSession session,@RequestBody OrderItem orderItem) {
		Users loggedInUser = (Users) session.getAttribute("logState");
		boolean ifSuccess = cService.deleteAllCartItem(loggedInUser.getUsersId());
		OrderItem insertOrderItem = oService.insertOrderItem(orderItem,loggedInUser);
		if(ifSuccess) {
			return "結帳成功";
		}
		return "結帳失敗";
	}
	
}
