package tw.tutorlink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.servlet.http.HttpSession;
import tw.tutorlink.bean.Users;
import tw.tutorlink.service.UsersService;

@RestController
public class InfomationContorller {

	@Autowired
	private UsersService uService;

	@PostMapping("/infomation")
	@ResponseBody
	public Users infomation(HttpSession session,@CookieValue("UsersId") String cookie) {
//		Users loggedInUser = (Users) session.getAttribute("logState");
//		Users result = new Users();
//		if (loggedInUser == null) {
//			result.setGoogleSubId("null");
//			result.setUserEmail("null");
//			return result;
//		}
		int cookieid = Integer.parseInt(cookie);
		return uService.findByIdDetail(cookieid);
	}

	@PostMapping("/send")
	@ResponseBody
	public String data(@RequestBody String str, HttpSession session,@CookieValue("UsersId") String cookie) {
		System.out.println(str);
		// 字串轉JSON
		JsonObject json = JsonParser.parseString(str).getAsJsonObject();
		String name = json.get("UserName").getAsString();
		String phone = json.get("Phone").getAsString();
		String city = json.get("City").getAsString();
		Long birth =json.get("Birthday").getAsLong();
		System.out.println(birth);
		
		// 用session抓ID
		Users loggedInUser = (Users) session.getAttribute("logState");
		@SuppressWarnings("unused")
		int sessionid = loggedInUser.getUsersId();
		
		// 用cookie抓ID
		int cookieid = Integer.parseInt(cookie);
		
		uService.setData(cookieid, name, phone, city, birth);
		return "ok";
	}
	
	@PostMapping("/pwdverifty")
	@ResponseBody
	public String pwd (@RequestBody String str,HttpSession session,@CookieValue("UsersId") String cookie) {
		JsonObject json = JsonParser.parseString(str).getAsJsonObject();
		String oldpwd = json.get("oldPwd").getAsString();
		String newPwd = json.get("newPwd").getAsString();
		String newPwd2 = json.get("newPwd2").getAsString();
		System.out.println(oldpwd+" "+newPwd+" "+newPwd2);
		System.out.println(cookie);
		return "ok";
	}
}
	