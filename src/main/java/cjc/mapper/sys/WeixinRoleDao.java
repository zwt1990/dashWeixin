package cjc.mapper.sys;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

import cjc.entity.sys.WeixinRole;

public interface WeixinRoleDao extends CrudRepository< WeixinRole, Serializable>{

	public WeixinRole findByRoleId(Integer roleId);
	
}
