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
		message.setText("親愛的TutorLink會員您好: \r\n"
				+ "\r\n"
				+ "驗證碼:"+content+"\r\n"
				+ "有效期間3分鐘，請盡快輸入驗證碼\r\n"
				+ "\r\n"
				+ "如果並非您本人申請，請立即變更 TutorLink 密碼以確保帳號安全。\r\n"
				+ "\r\n"
				+ "如果驗證碼無法使用，請重整頁面並申請新的驗證碼\r\n"
				+ "\r\n"
				+ "感謝您\r\n"
				+ "TutorLink 客服團隊");
		mailSender.send(message);
		System.out.println("成功寄信");
		return "成功寄信";
	};

}
