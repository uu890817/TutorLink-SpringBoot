package tw.tutorlink.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
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
	public String forgetMail(String mail) {
		Random random = new Random();
		int min = 100000; // 最小值（包括）
		int max = 999999; // 最大值（包括）
		int randomNumber = random.nextInt(max - min + 1) + min;

		Users result = uService.forgetMail(mail, randomNumber);
		System.out.println("隨機生成的數值: " + randomNumber);

		if (result != null) {
			String mailresult = mService.sendVerifyMail(mail, randomNumber);
			if (mailresult.equals("成功寄信")) {
				return "ok";
			}
		}
		return "fail";
	}
}
