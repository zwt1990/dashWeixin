package cjc.mapper.weixin;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cjc.entity.weixin.AlbumConfig;

public interface WeixinImgConfigDao extends CrudRepository<AlbumConfig, Serializable>{

	public List<AlbumConfig> findByConfigIdAndStatus(Integer configId,Integer status);
	
	public List<AlbumConfig> findByConfigIdAndStatusAndCategory(Integer configId,Integer status,Integer category);
}
