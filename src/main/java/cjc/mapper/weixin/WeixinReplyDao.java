package cjc.mapper.weixin;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import cjc.entity.weixin.WeixinReply;

public interface WeixinReplyDao extends CrudRepository< WeixinReply, Serializable>{
	
	
	public WeixinReply findByConfigIdAndEventType(Integer config,Integer eventType);
	
}
