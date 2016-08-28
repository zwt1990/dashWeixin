package cjc.service.weixin;

import java.util.List;

import cjc.dto.WechatConfig;
import cjc.entity.weixin.PhotoConfig;
import cjc.entity.weixin.WeixinConfig;

public interface WechatService {
	
	public WeixinConfig queryConfigByAppId(String appId);
	
	public WeixinConfig queryConfigByOrigId(String originalId);
	
	public WeixinConfig getWxConfig(Integer id);
	
	public WeixinConfig addWxConfig(WeixinConfig WxConfig);
	
	public List<WeixinConfig> allWeixinConfigs();
	
	
	public WeixinConfig createWXconfig(WeixinConfig weixinConfig);
	
	/**
	 * 根据Id获取WeixinReply
	 * @param configId
	 * @return
	 */
	public WechatConfig getReplyByConfig(Integer configId);
	
	public List<WechatConfig> getReplyBys();
	
	public void updateReply(Integer configId,String welContext,String replyContext,boolean openCustomer);
	
}
