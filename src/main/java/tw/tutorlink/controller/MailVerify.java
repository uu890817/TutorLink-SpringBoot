package tw.tutorlink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tw.tutorlink.service.MailService;

@RestController
public class MailVerify {

	@Autowired
	private MailService mService;

	@PostMapping("/testmail")
	@ResponseBody
	public void mService(String mail) {
		mService.sendVerifyMail(mail);
	}

}
