package cjc.weixin.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cjc.entity.weixin.WeixinConfig;
import cjc.entity.weixin.WeixinReply;
import cjc.service.weixin.WechatService;
import cjc.weixin.sdk.WeiXinReplyMessages;
import cjc.weixin.sdk.msg.BaseMessage;
import cjc.weixin.sdk.msg.ReceivedMessage;

@Component
public class SubscribeHandler extends BaseEventHandler{
	
	@Autowired
	private WechatService	wechatService;
	
	public BaseMessage handle(ReceivedMessage receivedMsg){
		WeixinConfig weixinConfig=wechatService.queryConfigByOrigId(receivedMsg.getToUserName());
		WeixinReply reply=wechatService.getReplyByEvent(weixinConfig.getId(), WeixinReply.EVENT_SUB);
		return WeiXinReplyMessages.createTextMessage(reply.getContext(), receivedMsg);
	}
}
