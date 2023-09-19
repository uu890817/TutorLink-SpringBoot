package tw.tutorlink.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.OrderItem;
import tw.tutorlink.dto.cart.OrderDTO;
import tw.tutorlink.dto.cart.RevenueDTO;
import tw.tutorlink.repository.CartDAO;
import tw.tutorlink.repository.OrderItemDAO;

@Service
public class OrderItemService {

	@Autowired
	private OrderItemDAO oDAO;

	public List<OrderDTO> findAllOrder() {
		List<OrderDTO> oDTOs = new ArrayList<>();
		List<OrderItem> oitems = oDAO.findAll();
		for (OrderItem oitem : oitems) {
			OrderDTO oDTO = new OrderDTO(oitem);
			oDTOs.add(oDTO);
		}
		return oDTOs;
	}

	public List<OrderDTO> getUserOrder(int uid) {
		List<OrderDTO> oDTOs = new ArrayList<>();
		List<OrderItem> oitems = oDAO.findOrderByUserId(uid);
		for (OrderItem oitem : oitems) {
			OrderDTO oDTO = new OrderDTO(oitem);
			oDTOs.add(oDTO);
		}
		return oDTOs;
	}

	public List<OrderDTO> getUserApplyRefund(int uid) {
		List<OrderDTO> oDTOs = new ArrayList<>();
		List<OrderItem> oitems = oDAO.findApplyRefundById(uid);
		for (OrderItem oitem : oitems) {
			OrderDTO oDTO = new OrderDTO(oitem);
			oDTOs.add(oDTO);
		}
		return oDTOs;
	}

	public List<OrderDTO> getUserRefund(int uid) {
		List<OrderDTO> oDTOs = new ArrayList<>();
		List<OrderItem> oitems = oDAO.findRefundById(uid);
		for (OrderItem oitem : oitems) {
			OrderDTO oDTO = new OrderDTO(oitem);
			oDTOs.add(oDTO);
		}
		return oDTOs;
	}

	public OrderItem insertOrderItem(OrderItem item) {
		OrderItem result = oDAO.save(item);
		if (result != null) {
			return result;
		}
		return null;
	}

	public OrderItem getOrderById(int oId) {
		OrderItem oitem = oDAO.findOrderByOrderId(oId);
		if (oitem != null) {
			return oitem;
		}
		return null;
	}

	public OrderItem updateOrderStates(int oId) {
		OrderItem oitem = oDAO.findOrderByOrderId(oId);
		oitem.setOrderStates(2);
		oDAO.save(oitem);
		return null;
	}

	public RevenueDTO countRevenue() {
		Integer videosRevenue=0;
		Integer lessonsRevenue=0;
		List<OrderItem> videos = oDAO.findVideoRevenue();
		List<OrderItem> lessons = oDAO.findLessonRevenue();
		for (OrderItem video : videos) {
			System.out.println(videosRevenue);
			System.out.println(video.getOrderId());
			videosRevenue+=video.getLesson().getPrice();
		}
		for (OrderItem lesson : lessons) {
			System.out.println(lessonsRevenue);
			System.out.println(lesson.getOrderId());
			lessonsRevenue+=lesson.getLesson().getPrice();
		}
		RevenueDTO rDTO=new RevenueDTO();
		rDTO.setLessons(lessonsRevenue);
		rDTO.setVideos(videosRevenue);
		return rDTO;
	}

}
