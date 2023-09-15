package tw.tutorlink.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.CartItem;
import tw.tutorlink.dto.cart.CartItemDTO;
import tw.tutorlink.repository.CartDAO;

@Service
public class CartService {

	@Autowired
	private CartDAO cDAO;

	// 刪除購物車商品
	public String deleteCartItem(Integer cId) {
		cDAO.deleteById(cId);
		return "OK";
	}

	// 刪除購物車所有商品
	public boolean deleteAllCartItem(Integer uId) {
		return cDAO.deleteCartByUserId(uId);
	}
	
	// 查詢使用者購物車商品
	public List<CartItemDTO> getUserShoppingCart(int id) {
		List<CartItemDTO> cDTOs = new ArrayList<>();
		List<CartItem> citems = cDAO.findByUsers(id);
		for (CartItem citem : citems) {
			CartItemDTO cDTO = new CartItemDTO(citem);
			cDTOs.add(cDTO);
		}
		return cDTOs;
	}

	// 更新購物車商品
	public CartItem updateCartItem(CartItemDTO cDTO) {
		CartItem findById = cDAO.findBycId(cDTO.getCartId());
		findById.setQuantity(cDTO.getQuantity());
		findById.setSelectedTimes(cDTO.getSelectedTimes());
		CartItem result = cDAO.save(findById);
		if (result != null) {
			return result;
		}
		return null;
	}

	// 新增商品至購物車
	public CartItem insertNewCartItem(CartItem item) {
		CartItem result = cDAO.save(item);
		if (result != null) {
			return result;
		}
		return null;
	}
}
