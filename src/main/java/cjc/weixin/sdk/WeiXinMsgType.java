package cjc.weixin.sdk;

import org.apache.commons.lang3.ArrayUtils;


public abstract class WeiXinMsgType {
	
	public static final String KF_MSG_TYPE="transfer_customer_service";
	
	public static boolean isEvent(String msgType) {
		return "event".equals(msgType);
	}
	
	public static boolean isInput(String msgType) {
		String msgTypes[]={"text","image","voice","video","shortvideo","location","link"};
		return  ArrayUtils.contains(msgTypes,msgType);
	}
	public static boolean isText(String msgType) {
		return "text".equals(msgType);
	}
	public static boolean isImage(String msgType) {
		return "image".equals(msgType);
	}
	
	public static boolean isVoice(String msgType) {
		return "voice".equals(msgType);
	}
	public static boolean isVideo(String msgType) {
		return "video".equals(msgType);
	}
	
	public static boolean isShortVideo(String msgType) {
		return "shortvideo".equals(msgType);
	}
	public static boolean isLocation(String msgType) {
		return "location".equals(msgType);
	}
	public static boolean isLink(String msgType) {
		return "link".equals(msgType);
	}
}
