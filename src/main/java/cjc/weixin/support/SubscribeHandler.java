package cjc.weixin.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cjc.weixin.sdk.msg.BaseMessage;
import cjc.weixin.sdk.msg.ReceivedMessage;

@Component
public class SubscribeHandler extends BaseEventHandler{
	
	public BaseMessage handle(ReceivedMessage receivedMsg){
		return null;
	}
}
