package cjc.weixin.support;

import org.springframework.stereotype.Component;

import cjc.weixin.sdk.msg.BaseMessage;
import cjc.weixin.sdk.msg.ReceivedMessage;
@Component
public class InputTextHandler extends BaseEventHandler{

	@Override
	public BaseMessage handle(ReceivedMessage receivedMsg) {
//		CustomerServerMessage customerServerMessage=new CustomerServerMessage();
//		customerServerMessage.setCreateTime(createTime);
		return null;
	}

}
