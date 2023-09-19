package tw.tutorlink.websocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import tw.tutorlink.bean.Users;

@Controller
@RequestMapping("/ws")
public class WebSocketController {

	@GetMapping("/userId")
	@ResponseBody
	public String getUserId(HttpSession session) {
		try {

			Users user = (Users) session.getAttribute("logState");
			return user.getUsersId().toString();
		} catch (Exception e) {
			System.err.println("Session無法取得");
			return "-1";
		}

	}


	
	
	
	
	
	
	
	
}
