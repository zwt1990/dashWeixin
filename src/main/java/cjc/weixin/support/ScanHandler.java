package cjc.weixin.support;

import org.springframework.stereotype.Component;

import cjc.weixin.sdk.WeiXinReplyMessages;
import cjc.weixin.sdk.msg.BaseMessage;
import cjc.weixin.sdk.msg.ReceivedMessage;
import cjc.weixin.sdk.msg.activity.TextMessage;

@Component
public class ScanHandler extends BaseEventHandler{
	
	public BaseMessage handle(ReceivedMessage receivedMsg){
		return null;
	}
}
