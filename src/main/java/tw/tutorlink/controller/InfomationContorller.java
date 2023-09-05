package tw.tutorlink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import tw.tutorlink.bean.Users;
import tw.tutorlink.service.UsersService;

@RestController
public class InfomationContorller {

	@Autowired
	private UsersService uService;

	@PostMapping("/infomation")
	@ResponseBody
	public Users infomation(HttpSession session) {
		Users loggedInUser = (Users) session.getAttribute("logState");
		Users result = new Users();
		if(loggedInUser == null ) {
			result.setGoogleSubId("null");
			result.setUserEmail("null");	
			return result;
		}
		result.setGoogleSubId(loggedInUser.getGoogleSubId());
		result.setUserEmail(loggedInUser.getUserEmail());	
		return loggedInUser;
	}
}
