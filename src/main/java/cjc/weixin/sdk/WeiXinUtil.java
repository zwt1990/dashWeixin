package cjc.weixin.sdk;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import cjc.entity.weixin.WeixinConfig;
import cjc.utils.DateUtil;
import cjc.utils.HttpUtils;
import cjc.weixin.sdk.CustomMenu.CustomButton;
import cjc.weixin.sdk.msg.activity.AbstractMessage;
import cjc.weixin.sdk.msg.activity.ImageMessage;
import cjc.weixin.sdk.msg.activity.MusicMessage;
import cjc.weixin.sdk.msg.activity.NewsMessage;
import cjc.weixin.sdk.msg.activity.TextMessage;
import cjc.weixin.sdk.msg.activity.VideoMessage;
import cjc.weixin.sdk.msg.activity.VoiceMessage;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.qccr.commons.httpclient.HttpRequest;
import com.qccr.commons.httpclient.HttpResult;

public class WeiXinUtil {
	private final static Map<String,AccessToken> tokenMap=new HashMap<String,AccessToken>();
	
//	public static String getOpenId(HttpServletRequest request) {
//		String code = request.getParameter("code");
//		return HttpRequest.Get(WeiXinUrls.getOpenIdUrl(code))
//						  .execute()
//						  .getJsonData()
//						  .getString("openid");
//	}
//	
//	public static JSONObject getOpenIdAndToken(HttpServletRequest request) {
//		String code = request.getParameter("code");
//		return HttpRequest.Get(WeiXinUrls.getOpenIdUrl(code))
//						  .execute()
//						  .getJsonData();
//	}
	public static String getToken(WeixinConfig config) {
		AccessToken token=tokenMap.get(config.getAppId());
		if(token==null){
			return refreshToken( config);
		}
		if(DateUtil.getMarginSeconds(new Date(), token.getCacheTime())>token.getExpires_in()){
			return refreshToken( config);
		}
		return token.getAccess_token();
	}
	
	private static String refreshToken(WeixinConfig config){
	//	HttpResult result = HttpRequest.Get(WeiXinUrls.getTokenUrl(config)).execute();
		String result=HttpUtils.doGet(WeiXinUrls.getTokenUrl(config));
		AccessToken accessToken=JSONObject.parseObject(result, AccessToken.class);
		accessToken.setCacheTime(new Date());
		tokenMap.put(config.getAppId(), accessToken);
		return accessToken.getAccess_token();
	}
	
//	public static String sign(String raw) {
//		try {
//			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
//			crypt.reset();
//			crypt.update(raw.getBytes("UTF-8"));
//			return byteToHex(crypt.digest());
//		} catch(Exception ex) {
//			//throw new MarketRuntimeException(ex);
//		}
//	}
	
//	private static String byteToHex(final byte[] hash) {
//        Formatter formatter = new Formatter();
//        try {
//	        for (byte b : hash) {
//	            formatter.format("%02x", b);
//	        }
//	        return formatter.toString();
//        } finally {
//        	formatter.close();
//        }
//    }
	
	
	 /**
     * 发送一个主动消息（文本、图片、语音、视频、音乐、图文）
     * @param message
     *            消息（文本、图片、语音、视频、音乐、图文）
     * @throws WeixinException
     *             如果发生错误
     */
    private  static void sendMessae(WeixinConfig config,AbstractMessage message) throws WeixinException {
    	postWithJson(config,WeiXinUrls.getCustomerSendUrl(getToken(config)),message, GlobalError.class);
    }
    /**
     * 主动发送一个文本消息
     * @param toUser
     *            接收用户的OpenID
     * @param content
     *            发送内容
     * @throws WeixinException
     *             如果发送业务错误
     */
    public static void sendText(WeixinConfig config,String toUser, String content) throws WeixinException {
    	TextMessage msg = new TextMessage();
        msg.touser = toUser;
        msg.addContent(content);
        sendMessae(config,msg);
    }
    /**
     * 主动发送一组文章消息
     * @param toUser
     *            接收用户的OpenID
     * @param news
     *            文章消息，至少添加一个Article，最多10个
     * @throws WeixinException
     *             如果发送业务错误
     */
    public static void sendNews(WeixinConfig config,String toUser, NewsMessage.News news) throws WeixinException {
        NewsMessage msg = new NewsMessage();
        msg.touser = toUser;
        msg.news = news;
        sendMessae(config,msg);
    }
    /**
     * 主动发送一个图片消息
     * @param toUser
     *            接收用户的OpenID
     * @param mediaId
     *            图片的资源ID
     * @throws WeixinException
     *             如果发送业务错误
     */
    public static void sendImage(WeixinConfig config,String toUser, String mediaId) throws WeixinException {
        ImageMessage msg = new ImageMessage();
        msg.touser = toUser;
        msg.image.media_id = mediaId;
        sendMessae(config,msg);
    }

    /**
     * 主动发送一个语音消息
     * @param toUser
     *            接收用户的OpenID
     * @param mediaId
     *            语音资源ID
     * @throws WeixinException
     *             如果发送业务错误
     */
    public static void sendVoice(WeixinConfig config,String toUser, String mediaId) throws WeixinException {
        VoiceMessage msg = new VoiceMessage();
        msg.touser = toUser;
        msg.media_id = mediaId;
        sendMessae(config,msg);
    }

    /**
     * 主动发送一个视频消息
     * @param toUser
     *            接收用户的OpenID
     * @param mediaId
     *            视频资源ID
     * @param title
     *            视频标题【可选】
     * @param description
     *            视频描述【可选】
     * @throws WeixinException
     *             如果发送业务错误
     */
    public static void sendVideo(WeixinConfig config,String toUser, String mediaId, String title, String description) throws WeixinException {
        VideoMessage msg = new VideoMessage();
        msg.touser = toUser;
        msg.media_id = mediaId;
        msg.title = title;
        msg.description = description;
        sendMessae(config,msg);
    }

    /**
     * 主动发送一个音乐消息消息
     * @param toUser
     *            接收用户的OpenID
     * @param musicUrl
     *            普通音质的音乐地址
     * @param hqMusicUrl
     *            高音质的音乐地址
     * @param thumbMediaId
     *            缩略图的资源ID
     * @param title
     *            音乐名称【可选】
     * @param description
     *            音乐描述【可选】
     * @throws WeixinException
     *             如果发送业务错误
     */
    public static void sendMusic(WeixinConfig config,String toUser, String musicUrl, String hqMusicUrl, String thumbMediaId, String title, String description) throws WeixinException {
        MusicMessage msg = new MusicMessage();
        msg.touser = toUser;
        msg.musicurl = musicUrl;
        msg.hqmusicurl = hqMusicUrl;
        msg.title = title;
        msg.description = description;
        sendMessae(config,msg);
    }
    
    /**
     * 更新菜单
     * @param customMenu
     * @throws WeixinException
     */
    public static void updateMenu(WeixinConfig config,CustomButton customButton) throws WeixinException {
    	postWithJson(config,WeiXinUrls.getMenuCreateUrl(getToken(config)),customButton, GlobalError.class);
    }
    
    /**
     * 查询菜单
     * @param customMenu
     * @throws WeixinException
     */
    public static JSONObject getMenu(WeixinConfig config) throws WeixinException {
    	try{
    		return HttpRequest.Get(WeiXinUrls.getMenuGetUrl(getToken(config)))
					  .execute()
					  .getJsonData();
    	}catch(Exception e){
    		throw new WeixinException("method_getMenu_exception:", e.getMessage(), e.getCause());
    	}
    }
   
    /**
     * 申请带参数二维码
     * @author zwt
     * @param type 类型，永久或者临时
     * @param sceneId 场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
     * @param expreSeconds 该二维码有效时间，以秒为单位。 最大不超过1800。申请临时二维码时使用，永久二维码不使用。
     * @return 微信服务器返回ticket和有效时间
     * @throws WeixinException 如果发生错误
     */
    public static QrCodeResponse createQrcode(WeixinConfig config,QrCodeRequest.TYPE type, Integer sceneId,String sceneStr, Integer expreSeconds) throws WeixinException {
    		 QrCodeRequest qrcode = new QrCodeRequest(type, sceneId, sceneStr,expreSeconds);
    		 return postWithJson(config,WeiXinUrls.getQrcodeCreateUrl(getToken(config)), qrcode, QrCodeResponse.class);
    }

    
    protected static final <T> T postWithJson(WeixinConfig config,String url, Object param, Class<T> returnType) throws WeixinException{
    	HttpPost post = new HttpPost(url);
    	post.addHeader("Content-Type", "text/xml");
		post.setEntity(new StringEntity( JSONObject.toJSONString(param),"UTF-8"));
		String httpResult = HttpUtils.excute(post);
			GlobalError error =JSONObject.parseObject(httpResult, GlobalError.class);
			if(error.errcode!=null){
				 if (error.errcode == 40001) {
	                 // 如果token超时则重新获取
	                 // 重新请求
	                 url = url.replaceAll("access_token=[^&]+", "access_token=" +refreshToken(config));
	                 return postWithJson(config,url, param, returnType);
	             }
				if(error.errcode!=0){//
					throw new WeixinException(error.errcode,error.errmsg,null);
				}
			}
		T obj =null;
		try{
			obj=JSONObject.parseObject(httpResult, returnType);
		}catch(JSONException e){
			throw new WeixinException(url, "调用失败："+e.getMessage(), e.getCause());
		}
		return obj;
    }
}
