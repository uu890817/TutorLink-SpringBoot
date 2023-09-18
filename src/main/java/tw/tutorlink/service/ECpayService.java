package tw.tutorlink.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import tw.tutorlink.dto.cart.EcPayDTO;

@Service
public class ECpayService {

	public String ecpayCheckout(EcPayDTO request) {
		// 隨機生成UID
		String uuId = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 20);
		AllInOne all = new AllInOne("");
		
		AioCheckOutALL obj = new AioCheckOutALL();
		obj.setMerchantTradeNo(uuId);  // 設定商店訂單編號
        obj.setMerchantTradeDate(request.getMerchantTradeDate());  // 設定商店訂單日期
        obj.setTotalAmount(request.getTotalAmount());  // 設定交易總金額
        obj.setTradeDesc(request.getTradeDesc());  // 設定交易描述
        obj.setItemName(request.getItemName());  // 設定訂單名稱
        obj.setReturnURL(request.getReturnURL());  // 設定交易結果回傳網址
        obj.setClientBackURL(request.getClientBackURL());  // 設定商店轉跳網址
        obj.setNeedExtraPaidInfo(request.getNeedExtraPaidInfo());  // 是否需要額外付款資訊
		String form = all.aioCheckOut(obj, null);
		
		return form;
	}
}
