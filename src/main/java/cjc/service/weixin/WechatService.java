package cjc.service.weixin;

import java.util.List;

import cjc.dto.WechatConfig;
import cjc.entity.weixin.PhotoConfig;
import cjc.entity.weixin.WeixinConfig;
import cjc.entity.weixin.WeixinReply;

public interface WechatService {
	
	public WeixinConfig queryConfigByAppId(String appId);
	
	public WeixinConfig queryConfigByOrigId(String originalId);
	
	public WeixinConfig getWxConfig(Integer id);
	
	public List<WeixinConfig> allWeixinConfigs();
	
	public PhotoConfig insertImgConfig(Integer wxConfigId,Integer category,String url,String path,String name);
	
	public void deleteImgConfig(Integer id);
	
	public List<PhotoConfig> getUsefulImgs(Integer configId,Integer category);
	
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
