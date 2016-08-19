package cjc.common.weixin.sdk.pay;

import java.io.Serializable;

public class JSAPIPrepayResult implements Serializable{
	/**  */
	private static final long serialVersionUID = -6213125038557724142L;
	/** 应用id */
	private String appId;
	/** 时间戳 */
	private String timeStamp;
	/** nonceStr */
	private String nonceStr;
	/** 预支付id */
	private String prePayId;
	/** 签名类型 */
	private String signType;
	/**支付签名  */
	private String paySign;
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getNonceStr() {
		return nonceStr;
	}
	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}
	public String getPrePayId() {
		return prePayId;
	}
	public void setPrePayId(String prePayId) {
		this.prePayId = prePayId;
	}
	public String getSignType() {
		return signType;
	}
	public void setSignType(String signType) {
		this.signType = signType;
	}
	public String getPaySign() {
		return paySign;
	}
	public void setPaySign(String paySign) {
		this.paySign = paySign;
	}
}
