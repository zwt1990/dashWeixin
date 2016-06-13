package cjc.weixin.support;

import cjc.weixin.sdk.msg.BaseMessage;
import cjc.weixin.sdk.msg.ReceivedMessage;

public abstract class BaseEventHandler {

	public abstract BaseMessage handle(ReceivedMessage receivedMsg);
}
