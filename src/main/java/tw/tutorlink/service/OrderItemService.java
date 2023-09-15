package tw.tutorlink.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.CartItem;
import tw.tutorlink.bean.OrderItem;
import tw.tutorlink.bean.Users;
import tw.tutorlink.dto.cart.CartItemDTO;
import tw.tutorlink.repository.CartDAO;
import tw.tutorlink.repository.OrderItemDAO;

@Service
public class OrderItemService {

	@Autowired
	private OrderItemDAO oDAO;
	
	@Autowired
	private CartDAO cDAO;
	
	public List<CartItemDTO> getUserOrder(int id) {
		List<CartItemDTO> cDTOs = new ArrayList<>();
		List<CartItem> citems = cDAO.findOrder(id);
		for (CartItem citem : citems) {
			CartItemDTO cDTO = new CartItemDTO(citem);
			cDTOs.add(cDTO);
		}
		return cDTOs;
	}
	
	
	public OrderItem insertOrderItem(OrderItem item,Users user) {
		item.setUsers(user);
		List<CartItem> findByUsers = cDAO.findByUsers(user.getUsersId());
//		item.setCartItem(findByUsers);
		OrderItem result = oDAO.save(item);
		if (result != null) {
			return result;
		}
		return null;
	}
}
