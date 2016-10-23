package cjc.mapper.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cjc.dto.WeixinPhotoDTO;
import cjc.entity.sys.Menu;
import cjc.entity.sys.Role;
import cjc.entity.sys.User;

public interface UserAuthorityMapper {
	
	User queryUser(@Param(value="username") String username,@Param(value="password") String password);
	
	User getUser(@Param(value="id")Integer userId);
	
	List<Menu> getMenusByRoleId(@Param(value="roleId") Integer roleId);
	
	List<Role> getRolesByUserId(@Param(value="userId") Integer userId);
	
	List<Menu> getMenusByLevel(@Param(value="level") Integer level);
	
	List<WeixinPhotoDTO> getWxPhotosByRoleId(@Param(value="roleId") Integer roleId);
	
}
