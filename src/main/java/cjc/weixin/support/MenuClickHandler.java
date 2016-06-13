package cjc.weixin.support;

import org.springframework.stereotype.Component;

import cjc.weixin.sdk.msg.BaseMessage;
import cjc.weixin.sdk.msg.ReceivedMessage;

@Component
public class MenuClickHandler extends BaseEventHandler{
	
	
	public BaseMessage handle(ReceivedMessage receivedMsg){
		return null;
		
	}
	
}
