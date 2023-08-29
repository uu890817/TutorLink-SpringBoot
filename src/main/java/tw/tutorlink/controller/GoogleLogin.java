package tw.tutorlink.controller;

import java.net.MulticastSocket;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin(origins = "*") // 允许来自任何源的请求
public class GoogleLogin {
	
	@PostMapping("/googletoken")
	public String verifyToken( @RequestBody String aaa ) {
		System.out.println(aaa);
		return null;
	}

}
