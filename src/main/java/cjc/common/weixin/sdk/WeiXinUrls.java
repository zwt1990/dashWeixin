package cjc.common.weixin.sdk;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import cjc.entity.weixin.WeixinConfig;


public class WeiXinUrls {

	private static String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?"
								+ "grant_type=client_credential&"
								+ "appid=%s&"
								+ "secret=%s";
	
	private static String OPENID_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?"
								+ "appid=%s&"
								+ "secret=%s&"
								+ "code=%s&"
								+ "grant_type=authorization_code";

	private final static String AUTH_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?"
								+ "appid=%s&"
								+ "redirect_uri=%s&"
								+ "response_type=code&"
								+ "scope=snsapi_userinfo#wechat_redirect";

	private final static String JS_SDK_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?"
								+ "access_token=%s&type=jsapi";

	private final static String CODE_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?"
								+ "appid=%s&"
								+ "redirect_uri=%s&"
								+ "response_type=code&"
								+ "scope=snsapi_userinfo&"
								+ "state=123#wechat_redirect";
	//https://api.weixin.qq.com/sns/userinfo
	private final static String SNSAPI_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo?"
								+ "access_token=%s&"
								+ "openid=%s"
								+ "&lang=zh_CN";
	private final static String CUNSTOMER_SEND_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=%s";
	
	private final static String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";
	
	private final static String MENU_GET_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=%s";
	
	private final static String QRCODE_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=%s";
	/**模板消息地址*/
    private static final String TEMPLATE_MSG_URL="https://api.weixin.qq.com/cgi-bin/message/template/send?"
                                + "access_token=%s";
	
	private final static String QRCODE_TICKET_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=%s";
	
	public static String getTokenUrl(WeixinConfig weixinConfig) {
		return String.format(TOKEN_URL, weixinConfig.getAppId(), weixinConfig.getAppSecret());		
	}
	
	public static String getOpenIdUrl(WeixinConfig weixinConfig,String code) {
		return String.format(OPENID_URL,weixinConfig.getAppId(), weixinConfig.getAppSecret(), code);
	}
	public static String getUserinfoUrl(String openId,String token) {
		return String.format(SNSAPI_USERINFO_URL,token,openId);
	}
	public static String getAuthUrl(WeixinConfig weixinConfig,String bizCallBackUrl) {
		return String.format(AUTH_URL, weixinConfig.getAppId(), bizCallBackUrl);
	}
	
	public static String getJsSDKUrl(String token) {
		return String.format(JS_SDK_URL, token);
	}
	public static String getCustomerSendUrl(String token) {
		return String.format(CUNSTOMER_SEND_URL, token);
	}
	public static String getMenuCreateUrl(String token) {
		return String.format(MENU_CREATE_URL, token);
	}
	public static String getMenuGetUrl(String token) {
		return String.format(MENU_GET_URL, token);
	}
	public static String getQrcodeCreateUrl(String token) {
		return String.format(QRCODE_CREATE_URL, token);
	}

    public static String getTemplateMsgUrl(String token) {
        return String.format(TEMPLATE_MSG_URL, token);
    }
	

	public static String getQrcodTicketUrl(String ticket) {
		return String.format(QRCODE_TICKET_URL, ticket);
	}

//	public static String getCodeUrl(String redirectUri) {
//		try {
//			String encodedRedirectUri = URLEncoder.encode(redirectUri, "UTF-8");
//			return String.format(CODE_URL, APP_ID, encodedRedirectUri);
//		} catch (UnsupportedEncodingException e) {
//			throw new MarketHttpException(e);
//		}
//	}
}
