package tw.tutorlink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.repository.CartDAO;

@Service
public class CartService {

	@Autowired
	private CartDAO cDAO;
}
