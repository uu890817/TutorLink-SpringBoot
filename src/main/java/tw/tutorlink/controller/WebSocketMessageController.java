package tw.tutorlink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import tw.tutorlink.bean.Users;
import tw.tutorlink.dto.exercises.ResponseDTO;
import tw.tutorlink.service.WebSocketChatRoomService;

@RestController
@RequestMapping("/ws")
public class WebSocketMessageController {

	
	@Autowired
	WebSocketChatRoomService crService;
	
	
	
	
	
	
	
	public Integer getUserId(HttpSession session) {
		try {

			Users user = (Users) session.getAttribute("logState");
			return user.getUsersId();
		} catch (Exception e) {
			System.err.println("Session無法取得");
			return null;
		}

	}

	
	
	@GetMapping("/myRooms")
	public ResponseDTO getMyChatRoom(HttpSession session ) {
		Integer userId = getUserId(session);
		if(userId != null) {
			return new ResponseDTO(crService.getMyChatRoom(userId), 200, "OK");
		}
		return new ResponseDTO(null, 500, "Error");
	}
	
	
	
}
