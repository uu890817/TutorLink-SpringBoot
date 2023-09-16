package tw.tutorlink.controller;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import tw.tutorlink.bean.Users;
import tw.tutorlink.repository.UsersDAO;
import tw.tutorlink.service.MailService;
import tw.tutorlink.service.UsersService;

// 忘記密碼用，功能已完成
@RestController
public class MailVerifyController {

	@Autowired
	private MailService mService;

	@Autowired
	private UsersService uService;

	@Autowired
	private UsersDAO uDAO;

	@PostMapping("/forgetmail")
	@ResponseBody
	public String forgetMail(@RequestBody Object mail) {
		Random random = new Random();
		int min = 100000; // 最小值（包括）
		int max = 999999; // 最大值（包括）
		int randomNumber = random.nextInt(max - min + 1) + min;
		String mailcheck = mail.toString();
		// 檢查輸入的信箱是否存在，存在則寫入產生的隨機驗證碼進資料庫，以便識別
		Users result = uService.forgetMail(mailcheck, randomNumber);
		System.out.println("隨機生成的數值: " + randomNumber);

		if (result != null) {
			// 當回傳不為空時，透過java寄信給前端回傳的mail，並寫入該產生的密碼
			String mailresult = mService.sendVerifyMail(mailcheck, randomNumber);
			if (mailresult.equals("成功寄信")) {
				return "ok";
			}
		}
		return "fail";
	}

	@PostMapping("/sendverify")
	@ResponseBody
	public String sendVerify(@RequestBody String data) {
		JsonObject jsonObject = JsonParser.parseString(data).getAsJsonObject();
		String mail = jsonObject.get("mail").getAsString();
		int verify = jsonObject.get("verify").getAsInt();
		Users result = uService.checkMail(mail);
		Date nowDate = new Date();
		Date expiredDate = result.getExpiredTime();

		long minutes = ((nowDate.getTime()) - (expiredDate.getTime())) / (1000 * 60);

		if (result.getRamdonVerify() == verify) {
			if (minutes <= 5) {
				return "success";
			}
			return "overtime";
		}
		return "fail";
	}

	@PostMapping("/updatePwd")
	@ResponseBody
	public String updatePwd(@RequestBody String data) {
		JsonObject jsonObject = JsonParser.parseString(data).getAsJsonObject();
		String mail = jsonObject.get("mail").getAsString();
		String pwd = jsonObject.get("pwd").getAsString();
		String doublepwd = jsonObject.get("doublepwd").getAsString();
		Users result = uService.checkMail(mail);
		if (result != null && pwd.equals(doublepwd)) {
			if(result.getUserPassword()!=pwd) {
				BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
				String pwdEncoder = bCryptPasswordEncoder.encode(pwd);
				result.setUserPassword(pwdEncoder);
				uDAO.save(result);
				return "success";
			}
			return "pwdRepeat";
		}
		return null;
	}
}
