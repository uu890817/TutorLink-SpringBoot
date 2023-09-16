package tw.tutorlink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	@Autowired
	private JavaMailSender mailSender;

	public String sendVerifyMail(String mail, int content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("ispantutorlink@gmail.com");
		message.setTo(mail);
		message.setSubject("主旨：Hello TutorLink");
		message.setText("這是您的驗證碼: " + content + "，請輸入");
		mailSender.send(message);
		System.out.println("成功寄信");
		return "成功寄信";
	};

}
