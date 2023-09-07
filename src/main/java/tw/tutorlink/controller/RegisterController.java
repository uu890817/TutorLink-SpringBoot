package tw.tutorlink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import tw.tutorlink.bean.Users;
import tw.tutorlink.service.UsersService;

@RestController
public class RegisterController {

	@Autowired
	private UsersService uService;

	@PostMapping("/checkmail")
	@ResponseBody
	public String checkMail(@RequestBody String mail) {
		JsonObject json = JsonParser.parseString(mail).getAsJsonObject();
		String mailcheck = json.get("mail").getAsString();

		if (uService.checkMail(mailcheck) == null) {
			System.out.println(mailcheck);
			return "true";
		}
		return "false";
	}

	@PostMapping("/normalregister")
	@ResponseBody
	public String register(@RequestBody String data) {
		JsonObject json = JsonParser.parseString(data).getAsJsonObject();
		String name = json.get("name").getAsString();
		String mail = json.get("mail").getAsString();
		String pwd = json.get("pwd").getAsString();
		if (name != null && mail != null && pwd != null) {
			Users user =uService.register(name, mail, pwd);
			if (user != null) {
				return "註冊成功";
			}
			return "註冊失敗";
		}
		return "資料不齊全";
	}
}
