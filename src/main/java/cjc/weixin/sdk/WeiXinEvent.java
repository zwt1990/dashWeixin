package cjc.weixin.sdk;

public abstract class WeiXinEvent {
	public static boolean isSubscribe(String event) {
		return "subscribe".equals(event);
	}
	
	public static boolean isUnsubscribe(String event) {
		return "unsubscribe".equals(event);
	}
	public static boolean isClick(String event) {
		return "CLICK".equals(event);
	}
	
}
