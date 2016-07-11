package cjc.service.weixin.impl;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cjc.dto.WechatConfig;
import cjc.entity.weixin.PhotoConfig;
import cjc.entity.weixin.WeixinConfig;
import cjc.entity.weixin.WeixinReply;
import cjc.mapper.weixin.WeixinConfigDao;
import cjc.mapper.weixin.WeixinPhotoConfigDao;
import cjc.mapper.weixin.WeixinReplyDao;
import cjc.service.weixin.WechatService;

@Service("wechatService")
public class WechatServiceImpl implements  WechatService{

	@Autowired
	private WeixinConfigDao weixinConfigDao;
	
	@Autowired
	private WeixinPhotoConfigDao	weixinImgConfigDao;
	
	@Autowired
	private WeixinReplyDao weixinReplyDao;
	@Override
	public WeixinConfig queryConfigByAppId(String appId) {
		return weixinConfigDao.findByAppId(appId);
	}

	@Override
	public WeixinConfig queryConfigByOrigId(String originalId) {
		return weixinConfigDao.findByOriginalId(originalId);
	}
	
	@Override
	public List<WeixinConfig> allWeixinConfigs() {
		Iterable<WeixinConfig>  configs=weixinConfigDao.findAll();
		return (List<WeixinConfig>) configs;
	}

	public PhotoConfig insertImgConfig(Integer wxConfigId,Integer category,String url,String path,String name) {
		PhotoConfig imgConfig=new PhotoConfig();
		imgConfig.setCategory(category);
		imgConfig.setConfigId(wxConfigId);
		imgConfig.setPath(path);
		imgConfig.setUrl(url);
		imgConfig.setCreateTime(new Date());
		imgConfig.setStatus(1);
		imgConfig.setName(name);
		return weixinImgConfigDao.save(imgConfig);
		
	}

	@Override
	public void deleteImgConfig(Integer id) {
		weixinImgConfigDao.delete(id);
	}

	@Override
	public List<PhotoConfig> getUsefulImgs(Integer configId,Integer category) {
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

	public WechatConfig getReplyByConfig(Integer configId) {
		return weixinReplyDao.queryReplyByconfig(configId);
	}

	@Override
	public void updateReply(Integer configId,String welContext,String replyContext,boolean openCustomer) {
		WeixinReply weixinReply=weixinReplyDao.get(configId);
		if(weixinReply==null){
			weixinReply=new WeixinReply();
			weixinReply.setCustomerFlag(openCustomer);
			weixinReply.setReplyContext(replyContext);
			weixinReply.setWelContext(welContext);
			weixinReply.setConfigId(configId);
			weixinReplyDao.save(weixinReply);
		}
		weixinReply.setCustomerFlag(openCustomer);
		weixinReply.setReplyContext(replyContext);
		weixinReply.setWelContext(welContext);
		weixinReplyDao.update(weixinReply);
	}

	@Override
	public List<WechatConfig> getReplyBys() {
		return weixinReplyDao.getReplys();
	}
	
}
