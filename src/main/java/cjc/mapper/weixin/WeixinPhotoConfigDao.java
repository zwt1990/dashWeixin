package cjc.mapper.weixin;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cjc.entity.weixin.PhotoConfig;

public interface WeixinPhotoConfigDao extends CrudRepository<PhotoConfig, Serializable>{

	public List<PhotoConfig> findByConfigIdAndStatus(Integer configId,Integer status);
	
	public List<PhotoConfig> findByConfigIdAndStatusAndCategory(Integer configId,Integer status,Integer category);
}
