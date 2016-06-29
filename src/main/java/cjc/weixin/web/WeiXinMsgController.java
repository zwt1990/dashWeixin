package cjc.weixin.web;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.eclipse.persistence.jaxb.JAXBMarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cjc.weixin.sdk.WeiXinEvent;
import cjc.weixin.sdk.WeiXinMsgType;
import cjc.weixin.sdk.WeiXinReplyMessages;
import cjc.weixin.sdk.WeiXinSignUtil;
import cjc.weixin.sdk.msg.ReceivedMessage;
import cjc.weixin.support.InputTextHandler;
import cjc.weixin.support.MenuClickHandler;
import cjc.weixin.support.ScanHandler;
import cjc.weixin.support.SubscribeHandler;

@Controller
public class WeiXinMsgController {
	private final static Logger log = Logger.getLogger(WeiXinMsgController.class);

	
	
//	@Autowired
//	@Qualifier(value="jaxb2Marshaller")
//	private Jaxb2Marshaller marshaller;
	@Autowired
	private MenuClickHandler menuClickHandler;
	@Autowired
	private SubscribeHandler subscribeHandler;
	@Autowired
	private ScanHandler scanHandler;
	@Autowired
	private InputTextHandler inputTextHandler;
	
	@RequestMapping(value="weixin/msg", method = RequestMethod.GET)
	@ResponseBody
	public String access(@RequestParam Map<String,String> params) {
		String[] verifyInfo = { 
			"", params.get("timestamp") /*时间戳*/, params.get("nonce")/*随机数*/ };
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

	@RequestMapping(value="weixin/msg", method = RequestMethod.POST)
	@ResponseBody
	public String message(@RequestParam Map<String,String> params, HttpServletRequest request) {
		try {
			String userInput = getUserInput(params, request.getReader());
			ReceivedMessage receivedMsg = parseUserInput(userInput);
			String msgId = receivedMsg.getMsgId();
			System.out.println("接受被动事件---------"+msgId);
//			if(StringUtils.isNotBlank(receivedMsg.getMsgId()) && 
//				!RedisDB.newKey(Keys.WeiXin.forRedisWeiXinMsg(msgId), TimeUtils.dayConverSecond(1))) {
//				return StringUtils.EMPTY;
//			}
			if(WeiXinMsgType.isInput(receivedMsg.getMsgType())){
				return sendXMLToWeiXin(WeiXinReplyMessages.transferCustomerService(receivedMsg), params);
			}
			if(WeiXinEvent.isUnsubscribe(receivedMsg.getEvent())) {
				return StringUtils.EMPTY;
			}

			if(WeiXinEvent.isSubscribe(receivedMsg.getEvent())) {
				return sendXMLToWeiXin(subscribeHandler.handle(receivedMsg), params);
			}

			if(WeiXinEvent.isClick(receivedMsg.getEvent())) {
				return sendXMLToWeiXin(menuClickHandler.handle(receivedMsg), params);
			}
			if(WeiXinEvent.isScan(receivedMsg.getEvent())) {
				return sendXMLToWeiXin(scanHandler.handle(receivedMsg), params);
			}
			
			return StringUtils.EMPTY;
		} catch (Exception ex) {
			log.error(ex);
			return StringUtils.EMPTY;
		}
	}
	
	
	
	private String getUserInput(Map<String,String> params, Reader inputReader) throws IOException {
		String msgSignature = params.get("msg_signature");
		String timestamp = params.get("timestamp");
		String nonce = params.get("nonce");
		String xmlText = IOUtils.toString(inputReader);
		String userInput = WeiXinSignUtil.decryptMsg(msgSignature, timestamp, nonce, xmlText);
		
		return userInput;
	}
	
	private ReceivedMessage parseUserInput(String userInput) throws JAXBException {
//		Unmarshaller unmarshaller = marshaller.getJaxbContext().createUnmarshaller();
//		Source xmlInputSource = new StreamSource(new StringReader(userInput));
//		ReceivedMessage receivedMsg = unmarshaller.unmarshal(xmlInputSource, ReceivedMessage.class).getValue();
//		return receivedMsg;
		return null;
	}
	
	private String sendXMLToWeiXin(Object message, Map<String,String> params) throws JAXBException {
		if(message==null){
			return StringUtils.EMPTY;
		}
		StringWriter strWriter = new StringWriter();
	//	marshaller.marshal(message, new StreamResult(strWriter));
		System.out.println(strWriter.toString());
		return WeiXinSignUtil.encryptMsg(strWriter.toString(), params.get("nonce"));
	}
}