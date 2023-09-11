package tw.tutorlink.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.CartItem;
import tw.tutorlink.bean.Users;
import tw.tutorlink.repository.CartDAO;
import tw.tutorlink.repository.UsersDAO;

@Service
public class CartService {
	
	@Autowired
	private CartDAO cDAO;
	
	@Autowired
	private UsersDAO uDAO;
	
	public List<CartItem> getUserShoppingCart(int id){
		Users user = uDAO.findById(id);
		if(user!=null) {	
			return uDAO.findById(id).getCart();
		}
		return null;
	}
	

}
