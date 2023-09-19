package tw.tutorlink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.servlet.http.HttpSession;
import tw.tutorlink.bean.Users;
import tw.tutorlink.service.ApplyTeacherService;
import tw.tutorlink.service.UsersService;

@RestController
public class ApplyTeacherController {

	@Autowired
	private UsersService uService;

	@Autowired
	private ApplyTeacherService atService;

	@PostMapping("/apply")
	@ResponseBody
	public String apply(@RequestBody String str, HttpSession session) {

		// 透過session取得id，以便知道是誰申請的
		Users loggedInUser = (Users) session.getAttribute("logState");
		int userid = loggedInUser.getUsersId();
		// 將各個值取出
		JsonObject json = JsonParser.parseString(str).getAsJsonObject();
		System.out.println(json);
		String name = json.get("name").getAsString(); // 姓名
		String idNumber = json.get("idNumber").getAsString(); // 身分證
		String country = json.get("country").getAsString(); // 國家
		String lessons = json.get("lessons").getAsString();// 授課科目
		JsonArray lang = json.get("lang").getAsJsonArray();
		Gson gson = new Gson();
		String langs = gson.toJson(lang); // 會說哪些語言
		System.out.println(langs);
		String exp = json.get("exp").getAsString(); // 授課經驗
		String jobstate = json.get("jobstate").getAsString(); // 工作狀態
		String hours = json.get("hours").getAsString(); // 預期授課時數
		String salary = json.get("salary").getAsString(); // 預期月收目標
		String good = json.get("good").getAsString(); // 優勢

		Users user = uService.findUsersByID(userid);
		if (user != null) {
			if (userid == user.getUsersId()) {
				if (name.equals(user.getUserDetail().getUserName())) {
					atService.applyTeacher(userid, name, idNumber, country, lessons, langs, exp, jobstate, hours,
							salary, good);
					return "ok";
				}
				return "申請人姓名有異";
			}
		}
		return null;
	}

	@PostMapping("/checkstate")
	@ResponseBody
	public String checkstate(HttpSession session) {
		Users loggedInUser = (Users) session.getAttribute("logState");
		int userid = loggedInUser.getUsersId();
		return atService.checkstate(userid);
	}

	@PostMapping("/reviewApply")
	@ResponseBody
	public String reviewApply(@RequestBody String json) {
		System.out.println(json);
		JsonObject jsondata = JsonParser.parseString(json).getAsJsonObject();
		int applyid = jsondata.get("applyid").getAsInt();
		String type = jsondata.get("type").getAsString();
		String name = jsondata.get("name").getAsString();
		System.out.println(applyid + " " + type + " " + name);
		String result = atService.findById(applyid);
		if (result.equals("ok")) {
			return "ok";
		}
		return "fail";
	}

}
