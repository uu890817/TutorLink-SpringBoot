package tw.tutorlink.dto.cart;

public class EcPayDTO {
	private String merchantTradeNo;
	private String merchantTradeDate;
	private String totalAmount;
	private String tradeDesc;
	private String itemName;
	private String returnURL;
	private String clientBackURL;
	private String needExtraPaidInfo;

	public EcPayDTO() {
	}

	public String getMerchantTradeNo() {
		return merchantTradeNo;
	}

	public void setMerchantTradeNo(String merchantTradeNo) {
		this.merchantTradeNo = merchantTradeNo;
	}

	public String getMerchantTradeDate() {
		return merchantTradeDate;
	}

	public void setMerchantTradeDate(String merchantTradeDate) {
		this.merchantTradeDate = merchantTradeDate;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getTradeDesc() {
		return tradeDesc;
	}

	public void setTradeDesc(String tradeDesc) {
		this.tradeDesc = tradeDesc;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getReturnURL() {
		return returnURL;
	}

	public void setReturnURL(String returnURL) {
		this.returnURL = returnURL;
	}

	public String getClientBackURL() {
		return clientBackURL;
	}

	public void setClientBackURL(String clientBackURL) {
		this.clientBackURL = clientBackURL;
	}

	public String getNeedExtraPaidInfo() {
		return needExtraPaidInfo;
	}

	public void setNeedExtraPaidInfo(String needExtraPaidInfo) {
		this.needExtraPaidInfo = needExtraPaidInfo;
	}

}
