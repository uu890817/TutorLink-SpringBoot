package tw.tutorlink.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;

@Service
public class ECpayService {

	public String ecpayCheckout() {
		// 隨機生成UID
		String uuId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);
		AllInOne all = new AllInOne("");
		
		AioCheckOutALL obj = new AioCheckOutALL();
		obj.setMerchantTradeNo(uuId);
		
		// 交易日期
		obj.setMerchantTradeDate("2023/09/17 08:05:23");
		
		// 交易總金額
		obj.setTotalAmount("5000");
		
		obj.setTradeDesc("線上課程");

		// 訂單名稱
		obj.setItemName("線上課程共10堂");

	    // 交易結果回傳網址，只接受 https 開頭的網站，可以使用 ngrok	
        obj.setReturnURL("http://211.23.128.214:5000");
		obj.setNeedExtraPaidInfo("N");
        // 商店轉跳網址 (Optional)
        obj.setClientBackURL("http://192.168.1.37:8080/");
		String form = all.aioCheckOut(obj, null);
		
		return form;
	}
}
