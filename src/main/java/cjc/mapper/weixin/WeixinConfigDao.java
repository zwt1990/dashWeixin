package cjc.mapper.weixin;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import cjc.entity.weixin.WeixinConfig;

public interface WeixinConfigDao extends CrudRepository<WeixinConfig, Serializable>{

	public WeixinConfig findByAppId(String appId);
	
	public WeixinConfig findByOriginalId(String originalId);
	
	
}
