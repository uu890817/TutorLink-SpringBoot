package tw.tutorlink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import tw.tutorlink.bean.Users;
import tw.tutorlink.service.UsersService;

@RestController
public class RouterVerify {

	@Autowired
	private UsersService uService;

	@PostMapping("/routerVerify")
	@ResponseBody
	public String routerVerify(HttpSession session, HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		Integer userId = -1;
		for (Cookie cookie : cookies) {
			System.out.println(cookie.getName() + ": " + cookie.getValue());
			if (cookie.getName().equals("UsersId")) {
				System.out.println("cookie: " + cookie.getValue());
				userId = Integer.parseInt(cookie.getValue());
				Users result = uService.findUsersByID(userId);
				
				if (session.getAttribute("logState") == null) {
					session.setAttribute("logState", result);
					session.setMaxInactiveInterval(600);
					return "loginOk";
				}
			}
			session.removeAttribute("logState");
			session.invalidate();
		}
		

		return "loginAgain";


	}
}
