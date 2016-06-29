package cjc.service.weixin;

import java.util.List;

import cjc.entity.weixin.WeixinConfig;
import cjc.entity.weixin.AlbumConfig;

public interface WechatService {
	
	public WeixinConfig queryConfigByAppId(String appId);
	
	public WeixinConfig getWxConfig(Integer id);
	
	public List<WeixinConfig> allWeixinConfigs();
	
	public AlbumConfig insertImgConfig(Integer wxConfigId,Integer category,String url,String path);
	
	public void deleteImgConfig(Integer id);
	
	public List<AlbumConfig> getUsefulImgs(Integer configId,Integer category);
	
	public WeixinConfig createWXconfig(WeixinConfig weixinConfig);
}
