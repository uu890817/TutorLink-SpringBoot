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
				if (session.getAttribute("logState") != null && userId != -1) {
					return "伺服器已重啟，請重新登入";
				} else {
					System.out.println("cookie: " + cookie.getValue());
					userId = Integer.parseInt(cookie.getValue());
					Users result = uService.findUsersByID(userId);

					if (session.getAttribute("logState") == null) {
						session.setAttribute("logState", result);
						session.setMaxInactiveInterval(600);
						return "loginOk";
					} else {
						// cookie存在且session存在 (網頁重新整理，且不關網頁或重啟server的情況)
						return "alreadylogin";
					}
				}
			}
		}
		// 找不到cookie，代表沒登入或者cookie時間已到，清除session
		session.removeAttribute("logState");
		session.invalidate();
		return "loginAgain";
	}
}
