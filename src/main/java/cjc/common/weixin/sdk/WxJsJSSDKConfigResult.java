package cjc.common.weixin.sdk;

import java.io.Serializable;
/**
 * 微信JS相关接口调用的验证
 * @author user
 *
 */
public class WxJsJSSDKConfigResult implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String appId;
	private String timeStamp;
	private String nonceStr;
	private String signature;
	private String jsSDKTicket;
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
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getJsSDKTicket() {
		return jsSDKTicket;
	}
	public void setJsSDKTicket(String jsSDKTicket) {
		this.jsSDKTicket = jsSDKTicket;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
