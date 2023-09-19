package tw.tutorlink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tw.tutorlink.dto.cart.EcPayDTO;
import tw.tutorlink.service.ECpayService;

@RestController
public class EcPayController {
	
	@Autowired
	ECpayService ecpayService;

	@PostMapping("/ecpay")
	@ResponseBody
	public String ecpayCheckout(@RequestBody EcPayDTO request) {
		System.out.println(request);
		String aioCheckOutALLForm = ecpayService.ecpayCheckout(request);
		
		return aioCheckOutALLForm;
	}
}