package tw.tutorlink.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
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
	public void deleteAllCartItem(Integer uid) {
		List<CartItem> citems = cDAO.findByUsers(uid);
		for (CartItem citem : citems) {
			citem.setStatus(2);
			CartItem result = cDAO.save(citem);
		}
	}

	// 查詢使用者購物車商品
	public List<CartItemDTO> getUserShoppingCart(int uid) {
		List<CartItemDTO> cDTOs = new ArrayList<>();
		List<CartItem> citems = cDAO.findByUsers(uid);
		for (CartItem citem : citems) {
			CartItemDTO cDTO = new CartItemDTO(citem);
			
			String savePath = "c:/temp/upload/image/";
			String imagePath = savePath+cDTO.getImage();
			System.err.println(imagePath);
			try {
				byte[] fileBytes = readFileToByteArray(imagePath);
		        String base64Image = Base64.getEncoder().encodeToString(fileBytes);
		        cDTO.setImage(base64Image);
			}catch(IOException e){
				e.printStackTrace();
				
			}
			cDTOs.add(cDTO);
		}
		return cDTOs;
	}

	// 查詢單筆購物車商品
	public CartItemDTO getShoppingCart(int cid) {
		CartItem citem = cDAO.findBycId(cid);
		CartItemDTO cDTO = new CartItemDTO(citem);
		return cDTO;
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

	public CartItem findByCartId(Integer cartId) {
		return cDAO.findBycId(cartId);
	}
	
	private byte[] readFileToByteArray(String filePath) throws IOException {
	    File file = new File(filePath);
	    FileInputStream fis = new FileInputStream(file);
	    byte[] fileBytes = new byte[(int) file.length()];
	    fis.read(fileBytes);
	    fis.close();
	    return fileBytes;
	}
}
