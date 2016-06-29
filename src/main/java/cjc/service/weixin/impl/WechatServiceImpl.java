package cjc.service.weixin.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cjc.entity.weixin.WeixinConfig;
import cjc.entity.weixin.AlbumConfig;
import cjc.mapper.weixin.WeixinConfigDao;
import cjc.mapper.weixin.WeixinImgConfigDao;
import cjc.service.weixin.WechatService;

@Service("wechatService")
public class WechatServiceImpl implements  WechatService{

	@Autowired
	private WeixinConfigDao weixinConfigDao;
	
	@Autowired
	private WeixinImgConfigDao	weixinImgConfigDao;
	
	@Override
	public WeixinConfig queryConfigByAppId(String appId) {
		return weixinConfigDao.findByAppId(appId);
	}

	@Override
	public List<WeixinConfig> allWeixinConfigs() {
		Iterable<WeixinConfig>  configs=weixinConfigDao.findAll();
		return (List<WeixinConfig>) configs;
	}

	public AlbumConfig insertImgConfig(Integer wxConfigId,Integer category,String url,String path) {
		AlbumConfig imgConfig=new AlbumConfig();
		imgConfig.setCategory(category);
		imgConfig.setConfigId(wxConfigId);
		imgConfig.setPath(path);
		imgConfig.setUrl(url);
		imgConfig.setCreateTime(new Date());
		imgConfig.setStatus(1);
		return weixinImgConfigDao.save(imgConfig);
		
	}

	@Override
	public void deleteImgConfig(Integer id) {
		weixinImgConfigDao.delete(id);
	}

	@Override
	public List<AlbumConfig> getUsefulImgs(Integer configId,Integer category) {
		if(category!=null){
			return weixinImgConfigDao.findByConfigIdAndStatusAndCategory(configId, 1,category);
		}
		return weixinImgConfigDao.findByConfigIdAndStatus(configId, 1);
	}

	@Override
	public WeixinConfig createWXconfig(WeixinConfig weixinConfig) {
		return weixinConfigDao.save(weixinConfig);
	}

	@Override
	public WeixinConfig getWxConfig(Integer id) {
		return weixinConfigDao.findOne(id);
	}
	
}