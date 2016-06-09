package cjc.weixin.sdk.web;

import org.springframework.stereotype.Component;

import com.toowell.market.common.weixin.sdk.msg.BaseMessage;
import com.toowell.market.common.weixin.sdk.msg.CustomerServerMessage;
import com.toowell.market.common.weixin.sdk.msg.ReceivedMessage;
@Component
public class InputTextHandler extends BaseEventHandler{

	@Override
	public BaseMessage handle(ReceivedMessage receivedMsg) {
//		CustomerServerMessage customerServerMessage=new CustomerServerMessage();
//		customerServerMessage.setCreateTime(createTime);
		return null;
	}

}
