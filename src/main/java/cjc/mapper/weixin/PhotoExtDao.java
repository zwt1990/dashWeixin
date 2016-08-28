package cjc.mapper.weixin;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import cjc.entity.weixin.PhotoExt;


public interface PhotoExtDao extends  CrudRepository<PhotoExt, Serializable>{

	public List<PhotoExt> findByPConfigId(Integer pconfigId);
	
}
