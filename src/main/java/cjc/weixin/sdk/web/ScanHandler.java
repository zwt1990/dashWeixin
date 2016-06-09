package cjc.weixin.sdk.web;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.toowell.market.common.weixin.sdk.msg.BaseMessage;
import com.toowell.market.common.weixin.sdk.msg.ReceivedMessage;
import com.toowell.market.common.weixin.sdk.msg.TextMessage;
import com.toowell.market.web.weixin.controller.WeiXinReplyMessages;

@Component
public class ScanHandler extends BaseEventHandler{
	public BaseMessage handle(ReceivedMessage receivedMsg){
		String context="openId:%s的用户扫描了参数为%s的二维码";
		context=String.format(context, receivedMsg.getFromUserName(),receivedMsg.getEventKey());
		TextMessage textReply = WeiXinReplyMessages.createTextMessage(context, receivedMsg);
		return textReply;
	}
}
