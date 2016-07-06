package cjc.service.weixin;

import java.util.List;

import cjc.entity.weixin.PhotoConfig;
import cjc.entity.weixin.WeixinConfig;
import cjc.entity.weixin.WeixinReply;

public interface WechatService {
	
	public WeixinConfig queryConfigByAppId(String appId);
	
	public WeixinConfig queryConfigByOrigId(String originalId);
	
	public WeixinConfig getWxConfig(Integer id);
	
	public List<WeixinConfig> allWeixinConfigs();
	
	public PhotoConfig insertImgConfig(Integer wxConfigId,Integer category,String url,String path);
	
	public void deleteImgConfig(Integer id);
	
	public List<PhotoConfig> getUsefulImgs(Integer configId,Integer category);
	
	public WeixinConfig createWXconfig(WeixinConfig weixinConfig);
	
	/**
	 * 根据Id和事件类型获取回复内容
	 * @param configId
	 * @param eventType
	 * @return
	 */
	public WeixinReply getReplyByEvent(Integer configId,Integer eventType);
}
