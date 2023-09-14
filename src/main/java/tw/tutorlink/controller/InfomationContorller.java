package tw.tutorlink.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tw.tutorlink.bean.Users;
import tw.tutorlink.dto.user.InfomationDTO;
import tw.tutorlink.service.UsersService;

@RestController
public class InfomationContorller {

	@Autowired
	private UsersService uService;

	@PostMapping("/infomation")
	@ResponseBody
	public InfomationDTO infomation(HttpSession session, @CookieValue("UsersId") String cookie) {
		Users loggedInUser = (Users) session.getAttribute("logState");
		int userid = loggedInUser.getUsersId();
		System.out.println("Session取得的ID : " + userid);
		InfomationDTO iDTO = uService.findByIdDetail(userid);
		if (iDTO.getGoogletoken() != null) {
			iDTO.setGoogletoken("google");
		}
		return iDTO;
	}

	// 使用者設定個人資料用
	@PostMapping("/send")
	@ResponseBody
	public String data(@RequestBody String str, HttpSession session, @CookieValue("UsersId") String cookie) {
		// 字串轉JSON
		JsonObject json = JsonParser.parseString(str).getAsJsonObject();
		String name = json.get("UserName").getAsString();
		String phone = json.get("Phone").getAsString();
		String city = json.get("City").getAsString();
		Long birth = json.get("Birthday").getAsLong();
		// 用session抓ID
		Users loggedInUser = (Users) session.getAttribute("logState");
		int sessionid = loggedInUser.getUsersId();
		uService.setData(sessionid, name, phone, city, birth);
		return "ok";
	}

	// --- 修改/驗證密碼用 ---
	@PostMapping("/pwdverifty")
	@ResponseBody
	public String pwd(@RequestBody String str, HttpSession session, @CookieValue("UsersId") String cookie,
			HttpServletResponse response) {
		JsonObject json = JsonParser.parseString(str).getAsJsonObject();
		String oldpwd = json.get("oldPwd").getAsString();
		String newPwd = json.get("newPwd").getAsString();
		String newPwd2 = json.get("newPwd2").getAsString();
		int cookieid = Integer.parseInt(cookie);
		String result = uService.findbyIdAndPwd(cookieid, oldpwd, newPwd, newPwd2);
//		if(result.equals("fail")) {
//			return "fail";
//		}
		return "ok";
	}

	// --- 回傳UserType，如果是老師就不會顯示申請老師頁面，且可以切換學生/老師頁面 ---
	@PostMapping("/type")
	@ResponseBody
	public Integer type(HttpSession session) {
		Users loggedInUser = (Users) session.getAttribute("logState");
		int userid = loggedInUser.getUsersId();
		Users result = uService.findUsersByID(userid);
		if (result != null) {
			return result.getUserType();
		}
		return null;
	}

	// 用在使用者頁面彈出視窗顯示名字用
	@PostMapping("/username")
	@ResponseBody
	public String username(HttpSession session) {
		Users loggedInUser = (Users) session.getAttribute("logState");
		int userid = loggedInUser.getUsersId();
		InfomationDTO result = uService.findByIdDetail(userid);
		if (result != null) {
			System.out.println(result.getUserName());
			return result.getUserName();
		}
		return null;
	}

	@PostMapping("/logintime")
	@ResponseBody
	public String logintime(HttpSession session) throws JSONException, ParseException {
		Users loggedInUser = (Users) session.getAttribute("logState");
		int userid = loggedInUser.getUsersId();
		Users result = uService.findUsersByID(userid);
		String lastLoginTime = result.getUserDetail().getLastLoginTime().toString();
		String newLoginTime = result.getUserDetail().getNewLoginTime().toString();
		SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		
		Date lastLoginOriginalDate = originalFormat.parse(lastLoginTime);
		Date newLoginOriginalDate = originalFormat.parse(newLoginTime);
		
		 // 創建SimpleDateFormat對象以格式化日期時間
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm	");
        String formattedlastDateTime = outputFormat.format(lastLoginOriginalDate);
        String formattednewDateTime = outputFormat.format(newLoginOriginalDate);
		JSONObject item = new JSONObject().put("LastLoginTime", formattedlastDateTime)
				.put("NewLoginTime", formattednewDateTime);
		return item.toString();
	}
}
