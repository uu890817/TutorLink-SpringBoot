package tw.tutorlink.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import tw.tutorlink.bean.Users;
import tw.tutorlink.dto.cart.CartItemDTO;
import tw.tutorlink.service.CartService;

@RestController
@RequestMapping("/shoppingcart")
public class ShoppingCartController {
	
	@Autowired
	private CartService cService;
	
	@GetMapping("/step1")
	@ResponseBody
	public List<CartItemDTO> getMyShoppingCart(HttpSession session) {
		Users loggedInUser = (Users) session.getAttribute("logState");
		return cService.getUserShoppingCart(loggedInUser.getUsersId());
	}
	
	@GetMapping("/CartItem")
	public List<CartItemDTO> testApi() {
		return cService.getUserShoppingCart(6);
	}
	
	
	
}
