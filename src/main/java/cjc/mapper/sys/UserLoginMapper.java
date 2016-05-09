package cjc.mapper.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cjc.entity.sys.UserLogin;

public interface UserLoginMapper {
	
	List<UserLogin> findUserPass(@Param(value="username") String username,@Param(value="password") String password);
	
}
