package cjc.weixin.sdk;

import cjc.entity.weixin.WeixinConfig;


public class WeiXinSignUtil {
	public static String decryptMsg(WeixinConfig config,String msgSignature, String timeStamp,
			String nonce, String encrypt_msg) {
		try {
			WXBizMsgCrypt pc = new WXBizMsgCrypt(config.getToken(), config.getEncodingAesKey(),config.getAppId());
			return pc.decryptMsg(msgSignature, timeStamp, nonce, encrypt_msg);
		} catch (AesException e) {
			return null;
		}		
	}

	public static String encryptMsg(WeixinConfig config,String replayMsg, String nonce) {
		try {
			WXBizMsgCrypt pc = new WXBizMsgCrypt(config.getToken(), config.getEncodingAesKey(),config.getAppId());
			return pc.encryptMsg(replayMsg, nonce);
		} catch (AesException e) {
			return null;
		}
	}
}
