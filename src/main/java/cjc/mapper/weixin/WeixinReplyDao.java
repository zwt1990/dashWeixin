package cjc.mapper.weixin;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cjc.dto.WechatConfig;
import cjc.entity.weixin.WeixinReply;

public interface WeixinReplyDao{
	
	
	public List<WechatConfig> getReplys();
	
	public int save(WeixinReply weixinReply);
	
	public WechatConfig queryReplyByconfig(@Param(value="configId")  Integer configId);
	
	public WeixinReply get(@Param(value="configId")  Integer configId);
	
	public void update(WeixinReply weixinReply);
}
