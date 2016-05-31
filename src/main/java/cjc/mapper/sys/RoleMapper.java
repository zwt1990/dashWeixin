package cjc.mapper.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cjc.entity.sys.Role;

public interface RoleMapper {
	
	public List<Role> findRolesByUserId(@Param(value="userId") Integer userId);
}
