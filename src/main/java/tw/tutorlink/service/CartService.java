package tw.tutorlink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import tw.tutorlink.bean.CartItem;
import tw.tutorlink.bean.Users;
import tw.tutorlink.repository.UsersDAO;

@Service
public class CartService {

	@Autowired
	private UsersService cDAO;
	
	@Autowired
	private UsersDAO uDAO;
	
	@Autowired
	private UsersService uService;
	
	//查看購物車
	public List<CartItem> getAllShoppingCartItem(HttpSession session){
		Users loggedInUser = (Users) session.getAttribute("logState");
		List<CartItem> cart = loggedInUser.getCart();
		
		return cart;
	}
	
//	//刪除購物車
//	public List<CartItem> deleteShoppingCartItem(HttpSession session) {
//		Users loggedInUser = (Users) session.getAttribute("logState");
//		List<CartItem> cart = loggedInUser.getCart();
//		return cart;
//	}
//	
//	//新增購物車
//	public List<CartItem> deleteShoppingCartItem(HttpSession session) {
//		Users loggedInUser = (Users) session.getAttribute("logState");
//		List<CartItem> cart = loggedInUser.getCart();
//		return cart;
//	}
	

}
