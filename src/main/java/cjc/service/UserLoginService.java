package cjc.service;

import java.util.List;

import cjc.entity.sys.UserLogin;


public interface UserLoginService {
	
	List<UserLogin> findByUsernameAndPassword(String username,String password);

}
