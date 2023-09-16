package tw.tutorlink.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import tw.tutorlink.bean.Users;
import tw.tutorlink.service.MailService;
import tw.tutorlink.service.UsersService;

@RestController
public class MailVerify {

	@Autowired
	private MailService mService;

	@Autowired
	private UsersService uService;

	@PostMapping("/forgetmail")
	@ResponseBody
	public String forgetMail(@RequestBody Object mail) {
//		Random random = new Random();
//		int min = 100000; // 最小值（包括）
//		int max = 999999; // 最大值（包括）
//		int randomNumber = random.nextInt(max - min + 1) + min;
//		System.out.println(mail.toString());
//		String mailcheck = mail.toString();
//		// 檢查輸入的信箱是否存在，存在則寫入產生的隨機驗證碼進資料庫，以便識別
//		Users result = uService.forgetMail(mailcheck, randomNumber);
//		System.out.println("隨機生成的數值: " + randomNumber);
//
//		if (result != null) {
//			// 當回傳不為空時，透過java寄信給前端回傳的mail，並寫入該產生的密碼
//			String mailresult = mService.sendVerifyMail(mailcheck, randomNumber);
//			if (mailresult.equals("成功寄信")) {
//				return "ok";
//			}
//		}
//		return "fail";
		return null;
	}

	@PostMapping("/sendverify")
	@ResponseBody
	public String sendVerify(@RequestBody String data) {
		JsonObject jsonObject = JsonParser.parseString(data).getAsJsonObject();
		String mail = jsonObject.get("mail").getAsString();
		int verify = jsonObject.get("verify").getAsInt();
		System.out.println(mail);
		System.out.println(verify);
		Users result = uService.checkMail(mail);
		System.out.println(result.getRamdonVerify());
		Date nowDate = new Date();
		Date expiredDate = result.getExpiredTime();

		long minutes = ((nowDate.getTime()) - (expiredDate.getTime())) / (1000 * 60);
		System.out.println("相差: " + minutes + " 分鐘");

		if (result.getRamdonVerify() == verify) {
			if(minutes <= 5) {
				return "success";
			}
			return "overtime";
		}
		return "fail";
	}
}
