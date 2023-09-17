package tw.tutorlink.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import tw.tutorlink.bean.ApplyTeacher;
import tw.tutorlink.bean.Users;
import tw.tutorlink.service.ApplyTeacherService;
import tw.tutorlink.service.UsersService;

@RestController
public class ManageController {

	@Autowired
	private UsersService uService;

	@Autowired
	private ApplyTeacherService atService;

	@PostMapping("/allusers")
	@ResponseBody
	public String allUsers(@RequestBody String json) throws JSONException, ParseException {
		// 回傳給前端的Jsno物件
		JSONObject responseJson = new JSONObject();
		// 取資料表總筆數
		long count = uService.count();
		JsonObject jsondadta = JsonParser.parseString(json).getAsJsonObject();
		int start = jsondadta.get("start").getAsInt();
		int rows = jsondadta.get("rows").getAsInt();
		JSONArray array = new JSONArray();
		List<Users> users = uService.findAllUsers(start, rows);
		// 用戶身分空值
		String type = "";
		// 用戶教師審核狀態空值
//		String review = "";

		// 判斷取值，有值就執行，先加進Json陣列，最後再加進Json物件回傳給前端
		if (users != null && !users.isEmpty()) {
			for (Users user : users) {
				type = (user.getUserType() == 1) ? "學生"
						: (user.getUserType() == 2) ? "老師" : (user.getUserType() == 3) ? "管理員" : "錯誤";
//				review = (user.getUserDetail().getTeacherState() == 1) ? "未申請"
//						: (user.getUserDetail().getTeacherState() == 2) ? "申請中"
//								: (user.getUserDetail().getTeacherState() == 3) ? "已通過"
//										: (user.getUserDetail().getTeacherState() == 4) ? "已退件" : "錯誤";
				SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
				String newLoginTime = "";

				if (user.getUserDetail().getNewLoginTime() == null) {
					newLoginTime = "2000-01-01 00:00:00.000";
				}else {
					newLoginTime = user.getUserDetail().getNewLoginTime().toString();
				}
				Date newLoginOriginalDate = originalFormat.parse(newLoginTime);

				SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy年MM月dd日 HH點mm分ss秒");
				String formattednewDateTime = outputFormat.format(newLoginOriginalDate);
				JSONObject item = new JSONObject().put("UsersId", user.getUsersId())
						.put("UserEmail", user.getUserEmail()).put("UserName", user.getUserDetail().getUserName())
						.put("UserType", type).put("LastLoginTime", formattednewDateTime);
				array = array.put(item);
			}
		}
		responseJson.put("count", count);
		responseJson.put("user", array);
		return responseJson.toString();
	}

	@PostMapping("/allapply")
	@ResponseBody
	public String allapply(@RequestBody String json) {
		// 回傳給前端的Jsno物件
		JSONObject responseJson = new JSONObject();
		// 取資料表總筆數
		long count = uService.count();
		JsonObject jsondadta = JsonParser.parseString(json).getAsJsonObject();
		int start = jsondadta.get("start").getAsInt();
		int rows = jsondadta.get("rows").getAsInt();
		JSONArray array = new JSONArray();
		List<ApplyTeacher> apply = atService.findAllApply(start, rows);

		return null;
	}
}
