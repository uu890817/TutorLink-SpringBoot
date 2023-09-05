package tw.tutorlink.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import tw.tutorlink.bean.CartItem;
import tw.tutorlink.bean.Exercises;
import tw.tutorlink.bean.Subject;
import tw.tutorlink.service.CartService;
import tw.tutorlink.service.LessonDetailService;
import tw.tutorlink.service.LessonsService;
import tw.tutorlink.service.SubjectService;

@RestController
@RequestMapping("/shoppingcart")
public class ShoppingCartController {
	
	@Autowired
	private LessonsService lService;
	
	@Autowired
	private LessonDetailService ldService;
	
	@Autowired
	private CartService cService;

	@GetMapping("/step1")
	@ResponseBody
	public List<CartItem> getMyShoppingCart(HttpSession session) {
		return cService.getAllShoppingCartItem(session);
	}
}
