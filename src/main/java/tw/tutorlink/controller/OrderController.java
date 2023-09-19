package tw.tutorlink.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import tw.tutorlink.bean.Users;
import tw.tutorlink.dto.cart.OrderDTO;
import tw.tutorlink.dto.cart.RevenueDTO;
import tw.tutorlink.service.OrderItemService;

@RestController
@RequestMapping("/purchase")
public class OrderController {
	
	@Autowired
	private OrderItemService orderItemService;
	
	
	@GetMapping("/all")
	@ResponseBody
	public List<OrderDTO> getMyPurchase(HttpSession session) {
		Users loggedInUser = (Users) session.getAttribute("logState");
		return orderItemService.getUserOrder(loggedInUser.getUsersId());
	}
	
	@GetMapping("/applyrefund")
	@ResponseBody
	public List<OrderDTO> getMyApplyRefund(HttpSession session) {
		Users loggedInUser = (Users) session.getAttribute("logState");
		return orderItemService.getUserApplyRefund(loggedInUser.getUsersId());
	}
	
	
	@GetMapping("/refund")
	@ResponseBody
	public List<OrderDTO> getRefund(HttpSession session) {
		Users loggedInUser = (Users) session.getAttribute("logState");
		return orderItemService.getUserRefund(loggedInUser.getUsersId());
	}
	
	@PutMapping("/clickrefund/{oid}")
	@ResponseBody
	public String applyRefund(@PathVariable Integer oid) {
		orderItemService.updateOrderStates(oid);
		return null;
	}
	
	@GetMapping("/manager/all")
	@ResponseBody
	public List<OrderDTO> getAllorder(HttpSession session) {
		return orderItemService.findAllOrder();
	}
	
	// TODO 平台
	@GetMapping("/manager/revenue")
	@ResponseBody
	public RevenueDTO getAllRevenue(HttpSession session) {
		return orderItemService.countRevenue();
	}
	
	// TODO 老師個人
	@GetMapping("/teacher/revenue")
	@ResponseBody
	public List<OrderDTO> getTeacherRevenue(HttpSession session) {
		return orderItemService.findAllOrder();
	}
	
}
