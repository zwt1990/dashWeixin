package cjc.weixin.sdk.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.qccr.knife.result.Result;
import com.qccr.userprod.facade.ro.user.ThirdPartyRO;
import com.toowell.market.common.MarketContext;
import com.toowell.market.common.weixin.sdk.msg.BaseMessage;
import com.toowell.market.common.weixin.sdk.msg.ReceivedMessage;
import com.toowell.market.common.weixin.sdk.msg.TextMessage;
import com.toowell.market.web.weixin.controller.WeiXinReplyMessages;

@Component
public class SubscribeHandler extends BaseEventHandler{
	@Autowired
	private com.qccr.userprod.facade.service.user.ThirdPartyFacade	thirdPartyFacade;
	
	public BaseMessage handle(ReceivedMessage receivedMsg){
		if(!StringUtils.isEmpty(receivedMsg.getEventKey())){//扫码关注
			String  eventKey=receivedMsg.getEventKey();
			String scene=eventKey.split("_")[1];//格式：qrscene_123123
			System.out.println(scene);
			if("1".equals(getSecenParam(scene,"channel"))){//活动
				String eventId=getSecenParam(scene, "eventId");
				recordUser( eventId, receivedMsg.getFromUserName());
			}
			String context=MarketContext.get("welcome_text");
			context=String.format(context, "test.qichechaoren.com","http://t.cn/R4yfrvL");
			return WeiXinReplyMessages.createTextMessage(context, receivedMsg);
		}
		String context=MarketContext.get("welcome_text");
		context=String.format(context, "test.qichechaoren.com","http://t.cn/R4yfrvL");
		return WeiXinReplyMessages.createTextMessage(context, receivedMsg);
	}
	
	private void recordUser(String eventId,String openId){
		Result<ThirdPartyRO> result=thirdPartyFacade.queryThirdParty(9,openId);
		if(result.getData()!=null){
			return;
		}
		ThirdPartyRO thirdParty=new ThirdPartyRO();
		thirdParty.setChannel(eventId);
		thirdParty.setThirdId(eventId);
		thirdParty.setSource(9);
		thirdPartyFacade.record(thirdParty);
	}
	
	private String getSecenParam(String scene,String key){
		String params[]=scene.split("&");
		Map<String, String> mapValues=new HashMap<String, String>();
 		for(String param:params ){
			String maps[]=param.split("=");
			mapValues.put(maps[0], maps[1]);
		}
 		return mapValues.get(key);
	}
}
