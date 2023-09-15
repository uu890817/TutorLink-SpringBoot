package tw.tutorlink.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	@Autowired
	private JavaMailSender mailSender;

	public void sendVerifyMail(String mail) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("ispantutorlink@gmail.com");
		message.setTo("gung.qixuan@gmail.com");
		message.setSubject("主旨：Hello TutorLink");
		message.setText("內容：這是一封測試信件，恭喜您成功發送了唷");
		mailSender.send(message);	
		System.out.println("成功寄信");
	};

}
