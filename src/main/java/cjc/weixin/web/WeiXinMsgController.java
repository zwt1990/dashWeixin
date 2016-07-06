package cjc.weixin.web;

import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cjc.entity.weixin.WeixinConfig;
import cjc.service.weixin.WechatService;
import cjc.utils.JaxbXmlUtil;
import cjc.weixin.sdk.WeiXinEvent;
import cjc.weixin.sdk.WeiXinMsgType;
import cjc.weixin.sdk.msg.ReceivedMessage;
import cjc.weixin.support.InputTextHandler;
import cjc.weixin.support.MenuClickHandler;
import cjc.weixin.support.ScanHandler;
import cjc.weixin.support.SubscribeHandler;

@Controller
public class WeiXinMsgController {
	private final static Logger log = Logger.getLogger(WeiXinMsgController.class);

	
	
	@Autowired
	private MenuClickHandler menuClickHandler;
	@Autowired
	private SubscribeHandler subscribeHandler;
	@Autowired
	private ScanHandler scanHandler;
	@Autowired
	private InputTextHandler inputTextHandler;
	@Autowired
	private WechatService	wechatService;
	
	
	@RequestMapping(value="weixin/{originalId}/msg", method = RequestMethod.GET)
	@ResponseBody
	public String access(@RequestParam Map<String,String> params,@PathVariable String originalId) {
		WeixinConfig wxConfig=wechatService.queryConfigByOrigId(originalId);
		String[] verifyInfo = { 
			wxConfig.getToken(), params.get("timestamp") /*时间戳*/, params.get("nonce")/*随机数*/ };
		Arrays.sort(verifyInfo);
		String bigStr = verifyInfo[0] + verifyInfo[1] + verifyInfo[2];  
		String digest = DigestUtils.sha1Hex(bigStr);
		String signature = params.get("signature");
		System.out.println(digest);
		if (digest.equals(signature)) {  
			return params.get("echostr");
		} 

		return "error";
	}

	@RequestMapping(value="weixin/{originalId}/msg", method = RequestMethod.POST)
	@ResponseBody
	public String message(@RequestParam Map<String,String> params,@PathVariable String originalId, HttpServletRequest request) {
		try {
			ReceivedMessage receivedMsg =JaxbXmlUtil.readConfigFromReader(ReceivedMessage.class,request.getReader());
			if(WeiXinMsgType.isInput(receivedMsg.getMsgType())){
				return sendXMLToWeiXin(inputTextHandler.handle(receivedMsg));
			}
			if(WeiXinEvent.isSubscribe(receivedMsg.getEvent())) {
				return sendXMLToWeiXin(subscribeHandler.handle(receivedMsg));
			}
			return StringUtils.EMPTY;
		} catch (Exception ex) {
			log.error(ex);
			return StringUtils.EMPTY;
		}
	}
	private String sendXMLToWeiXin(Object message) throws JAXBException {
		if(message==null){
			return StringUtils.EMPTY;
		}
		return JaxbXmlUtil.toXML(message);
	}
}