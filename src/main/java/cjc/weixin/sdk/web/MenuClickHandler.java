package cjc.weixin.sdk.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.qccr.knife.result.Result;
import com.qccr.userprod.facade.ro.user.ThirdPartyRO;
import com.toowell.market.common.MarketContext;
import com.toowell.market.common.weixin.sdk.msg.BaseMessage;
import com.toowell.market.common.weixin.sdk.msg.ReceivedMessage;
import com.toowell.market.common.weixin.sdk.msg.TextMessage;
import com.toowell.market.web.weixin.controller.WeiXinReplyMessages;

@Component
public class MenuClickHandler extends BaseEventHandler{
	
	@Autowired
	private com.qccr.userprod.facade.service.user.ThirdPartyFacade	thirdPartyFacade;
	
	public BaseMessage handle(ReceivedMessage receivedMsg){
		if(MarketContext.get("account_menu_key").equals(receivedMsg.getEventKey())){
			//Result<Integer> reslut=thirdPartyFacade.redirectUserId(9,receivedMsg.getFromUserName(),107001289);
			Result<ThirdPartyRO> result=thirdPartyFacade.queryThirdParty(9, receivedMsg.getFromUserName());
			if(result.isFailed()){
				return null;
			}
			ThirdPartyRO thirdPartyRO=result.getData();
			String context=String.format(MarketContext.get("unbind_account_text"),"www.qccr.com");
			if(thirdPartyRO!=null){//有绑定账号
				 context=String.format(MarketContext.get("binded_account_text"), thirdPartyRO.getPhone(),"www.qccr.com","www.qccr.com");
			}
			TextMessage textReply = WeiXinReplyMessages.createTextMessage(context, receivedMsg);
			return textReply;
		}
		return null;
		
	}
	
}
