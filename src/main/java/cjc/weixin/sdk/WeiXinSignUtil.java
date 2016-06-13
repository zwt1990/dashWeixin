package cjc.weixin.sdk;


public class WeiXinSignUtil {
	public static String decryptMsg(String msgSignature, String timeStamp,
			String nonce, String encrypt_msg) {
		try {
			WXBizMsgCrypt pc = new WXBizMsgCrypt("", "", "");
			return pc.decryptMsg(msgSignature, timeStamp, nonce, encrypt_msg);
		} catch (AesException e) {
			return null;
		}		
	}

	public static String encryptMsg(String replayMsg, String nonce) {
		try {
			WXBizMsgCrypt pc = new WXBizMsgCrypt("", "", "");
			return pc.encryptMsg(replayMsg, nonce);
		} catch (AesException e) {
			return null;
		}
	}
}
