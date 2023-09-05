package tw.tutorlink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.cloud.Date;
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
	public Users infomation(HttpSession session) {
		Users loggedInUser = (Users) session.getAttribute("logState");
		Users result = new Users();
		if (loggedInUser == null) {
			result.setGoogleSubId("null");
			result.setUserEmail("null");
			return result;
		}
		result.setGoogleSubId(loggedInUser.getGoogleSubId());
		result.setUserEmail(loggedInUser.getUserEmail());
		result.setUserPassword(null);
		return result;
	}

	@PostMapping("/send")
	@ResponseBody
	public String data(@RequestBody String str, HttpSession session) {
		System.out.println(str);
		JsonObject json = JsonParser.parseString(str).getAsJsonObject();
		String name = json.get("UserName").getAsString();
		String mail = json.get("userEmail").getAsString();
		String phone = json.get("Phone").getAsString();
		String city = json.get("City").getAsString();
		int birth = json.get("Birthday").getAsInt();
		System.out.println(name + " , " + mail + " , " + phone + " , " + city + " , " + birth);
		Users loggedInUser = (Users) session.getAttribute("logState");
		int id = loggedInUser.getUsersId();
		
		uService.setData(id, name, phone, city, birth);
		return "ok";
	}
}
