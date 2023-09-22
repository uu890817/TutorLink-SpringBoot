package tw.tutorlink.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tw.tutorlink.bean.OrderItem;
import tw.tutorlink.bean.Subject;
import tw.tutorlink.dto.cart.OrderDTO;
import tw.tutorlink.dto.cart.RevenueDTO;
import tw.tutorlink.dto.cart.SubjectRevenueDTO;
import tw.tutorlink.repository.OrderItemDAO;
import tw.tutorlink.repository.SubjectDAO;

@Service
public class OrderItemService {

	@Autowired
	private OrderItemDAO oDAO;

	@Autowired
	private SubjectDAO sDAO;

	public List<OrderDTO> findAllOrder() {
		List<OrderDTO> oDTOs = new ArrayList<>();
		List<OrderItem> oitems = oDAO.findAll();
		for (OrderItem oitem : oitems) {
			OrderDTO oDTO = new OrderDTO(oitem);
			String savePath = "c:/temp/upload/image/";
			String imagePath = savePath+oDTO.getImage();
			System.err.println(imagePath);
			try {
				byte[] fileBytes = readFileToByteArray(imagePath);
		        String base64Image = Base64.getEncoder().encodeToString(fileBytes);
		        oDTO.setImage(base64Image);
			}catch(IOException e){
				e.printStackTrace();
				
			}
			oDTOs.add(oDTO);
		}
		return oDTOs;
	}

	public List<OrderDTO> getUserOrder(int uid) {
		List<OrderDTO> oDTOs = new ArrayList<>();
		List<OrderItem> oitems = oDAO.findOrderByUserId(uid);
		for (OrderItem oitem : oitems) {
			OrderDTO oDTO = new OrderDTO(oitem);
			String savePath = "c:/temp/upload/image/";
			String imagePath = savePath+oDTO.getImage();
			try {
				byte[] fileBytes = readFileToByteArray(imagePath);
		        String base64Image = Base64.getEncoder().encodeToString(fileBytes);
		        oDTO.setImage(base64Image);
			}catch(IOException e){
				e.printStackTrace();
				
			}
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
			String savePath = "c:/temp/upload/image/";
			String imagePath = savePath+oDTO.getImage();
			try {
				byte[] fileBytes = readFileToByteArray(imagePath);
		        String base64Image = Base64.getEncoder().encodeToString(fileBytes);
		        System.err.println(base64Image);
		        oDTO.setImage(base64Image);
			}catch(IOException e){
				e.printStackTrace();
			}
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
		Integer videosRevenue = 0;
		Integer lessonsRevenue = 0;
		List<OrderItem> videos = oDAO.findVideoRevenue();
		List<OrderItem> lessons = oDAO.findLessonRevenue();
		for (OrderItem video : videos) {
			videosRevenue += video.getLesson().getPrice();
		}
		for (OrderItem lesson : lessons) {
			lessonsRevenue += lesson.getLesson().getPrice();
		}
		RevenueDTO rDTO = new RevenueDTO();
		rDTO.setLessons(lessonsRevenue);
		rDTO.setVideos(videosRevenue);
		return rDTO;
	}

	public RevenueDTO countTeacherRevenue(int uid) {
		Integer videosRevenue = 0;
		Integer lessonsRevenue = 0;
		List<OrderItem> videos = oDAO.findTeacherVideoRevenue(uid);
		List<OrderItem> lessons = oDAO.findTeacherLessonRevenue(uid);
		for (OrderItem video : videos) {
			videosRevenue += video.getLesson().getPrice();
		}
		for (OrderItem lesson : lessons) {
			lessonsRevenue += lesson.getLesson().getPrice();
		}
		RevenueDTO rDTO = new RevenueDTO();
		rDTO.setLessons(lessonsRevenue);
		rDTO.setVideos(videosRevenue);
		return rDTO;
	}

	public List<SubjectRevenueDTO> countRevenueBySubject() {
		List<SubjectRevenueDTO> srDtos = new ArrayList<>();
		List<Subject> findAllSubjects = sDAO.findAll();
		for (Subject subject : findAllSubjects) {
			Integer videosRevenue = 0;
			Integer lessonsRevenue = 0;
			SubjectRevenueDTO srDto = new SubjectRevenueDTO();
			List<OrderItem> videos = oDAO.findVideoRevenueBySubject(subject.getSubjectId());
			List<OrderItem> lessons = oDAO.findLessonRevenueBySubject(subject.getSubjectId());
			srDto.setSubject(subject.getSubjectContent());
			for (OrderItem video : videos) {
				videosRevenue += video.getLesson().getPrice();
			}
			for (OrderItem lesson : lessons) {
				lessonsRevenue += lesson.getLesson().getPrice();
			}
			srDto.setLessons(lessonsRevenue);
			srDto.setVideos(videosRevenue);
			srDtos.add(srDto);
		}
		return srDtos;
	}

	
	// 轉base64
	private byte[] readFileToByteArray(String filePath) throws IOException {
	    File file = new File(filePath);
	    FileInputStream fis = new FileInputStream(file);
	    byte[] fileBytes = new byte[(int) file.length()];
	    fis.read(fileBytes);
	    fis.close();
	    return fileBytes;
	}
}
