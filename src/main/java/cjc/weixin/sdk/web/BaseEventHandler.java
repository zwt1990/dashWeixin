package cjc.weixin.sdk.web;

import com.toowell.market.common.weixin.sdk.msg.BaseMessage;
import com.toowell.market.common.weixin.sdk.msg.ReceivedMessage;

public abstract class BaseEventHandler {

	public abstract BaseMessage handle(ReceivedMessage receivedMsg);
}
