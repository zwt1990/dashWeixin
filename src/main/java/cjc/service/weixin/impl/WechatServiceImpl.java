package cjc.service.weixin.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cjc.dto.WechatConfig;
import cjc.entity.weixin.WeixinConfig;
import cjc.entity.weixin.WeixinReply;
import cjc.mapper.weixin.WeixinConfigDao;
import cjc.mapper.weixin.WeixinReplyDao;
import cjc.service.weixin.WechatService;

@Service("wechatService")
public class WechatServiceImpl implements  WechatService{

	@Autowired
	private WeixinConfigDao weixinConfigDao;
	

	
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

	@Override
	public WeixinConfig addWxConfig(WeixinConfig WxConfig) {
		return weixinConfigDao.save(WxConfig);
	}
	
}
