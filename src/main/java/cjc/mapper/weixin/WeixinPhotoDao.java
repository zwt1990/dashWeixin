package cjc.mapper.weixin;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cjc.entity.weixin.WeixinPhoto;

public interface WeixinPhotoDao extends  CrudRepository<WeixinPhoto, Serializable>{
	
	public List<WeixinPhoto> findByConfigId(Integer configId);
}
