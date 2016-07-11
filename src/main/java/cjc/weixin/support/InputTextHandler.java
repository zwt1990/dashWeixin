package cjc.weixin.support;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cjc.dto.WechatConfig;
import cjc.entity.weixin.WeixinConfig;
import cjc.service.weixin.WechatService;
import cjc.weixin.sdk.WeiXinReplyMessages;
import cjc.weixin.sdk.msg.BaseMessage;
import cjc.weixin.sdk.msg.ReceivedMessage;
@Component
public class InputTextHandler extends BaseEventHandler{

	@Autowired
	private WechatService	wechatService;
	@Override
	public BaseMessage handle(ReceivedMessage receivedMsg) {
	WeixinConfig weixinConfig=wechatService.queryConfigByOrigId(receivedMsg.getToUserName());
	WechatConfig   replyConfig=wechatService.getReplyByConfig(weixinConfig.getId());
	if(replyConfig.getCustomerFlag()){
		return WeiXinReplyMessages.transferCustomerService(receivedMsg);
	}
	if(StringUtils.isEmpty(replyConfig.getReplyContext())){
		return null;
	}
		return WeiXinReplyMessages.createTextMessage(replyConfig.getReplyContext(), receivedMsg);
	}

}
