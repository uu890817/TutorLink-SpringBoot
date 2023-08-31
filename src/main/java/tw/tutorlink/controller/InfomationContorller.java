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
		System.out.println("測試撈session中資料: "+loggedInUser.getGoogleSubId());
		System.out.println("測試撈session中資料: "+loggedInUser.getGoogleSubId());
		System.out.println("測試撈session中資料: "+loggedInUser.getUsersId());
		return loggedInUser;
	}
}
